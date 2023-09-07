package com.ddd.ansayo.search

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.ddd.ansayo.base.BaseActivity
import com.ddd.ansayo.core_design.util.snackbar.SnackBarLineMax
import com.ddd.ansayo.data.SearchedQueryStore
import com.ddd.ansayo.databinding.ActivitySearchBinding
import com.ddd.ansayo.domain.model.search.keyword.SearchAction
import com.ddd.ansayo.domain.model.search.keyword.SearchMutation
import com.ddd.ansayo.presentation.viewmodel.search.SearchViewModel
import com.ddd.ansayo.search.keyword.SearchkeywordAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SearchActivity :
    BaseActivity<ActivitySearchBinding>(ActivitySearchBinding::inflate) {

    private val viewModel by viewModels<SearchViewModel>()
    private val keywordAdapter = SearchkeywordAdapter(
        keywordClickListener = {

        },
        deleteClickListener = {
            viewModel.onAction(SearchAction.ClickKeywordDelete(it))
        }
    )

    @Inject
    lateinit var searchedQueryStore: SearchedQueryStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        collectState()
        collectSideEffect()

        viewModel.onAction(SearchAction.EnteredScreen)
    }
    private fun initView() {
        binding.run {
            vSearch.showKeyboard()
            vSearch.searchText.observe(this@SearchActivity) {
                startActivity(SearchListActivity.getIntent(this@SearchActivity, it))
                search(it)
            }
            rvRecentKeyword.run {
                adapter = keywordAdapter
                itemAnimator = null
                setHasFixedSize(true)
            }
        }
    }
    private fun collectState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                launch {
                    viewModel.container.stateFlow
                        .map { it.keyword }
                        .distinctUntilChanged()
                        .collect{
                            keywordAdapter.updateItems(it)
                        }
                }
            }
        }
    }
    private fun collectSideEffect() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.container.sideEffectFlow.collect{
                    when(it) {
                        is SearchMutation.SideEffect.StartSearchResultScreen -> {

                        }
                        is SearchMutation.SideEffect.DeleteKeyword -> {
                        }
                        is SearchMutation.SideEffect.ShowSnackBar -> {
                            SnackBarLineMax(binding.root,it.message).show()
                        }
                    }

                }
            }
        }
    }
    private fun search(searchKeyword: String) {
        if (searchKeyword.isBlank()) {
            return
        }
        searchedQueryStore.addText(searchKeyword)
    }

    override fun onResume() {
        super.onResume()
        viewModel.onAction(SearchAction.EnteredScreen)
    }
}