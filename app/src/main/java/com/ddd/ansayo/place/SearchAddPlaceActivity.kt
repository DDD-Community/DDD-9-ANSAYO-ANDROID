package com.ddd.ansayo.place

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.ddd.ansayo.base.BaseActivity
import com.ddd.ansayo.core_model.place.mapToDto
import com.ddd.ansayo.databinding.ActivitySearchAddPlaceBinding
import com.ddd.ansayo.domain.model.place.SearchAddPlaceAction
import com.ddd.ansayo.domain.model.place.SearchAddPlaceMutation
import com.ddd.ansayo.presentation.viewmodel.Constant
import com.ddd.ansayo.presentation.viewmodel.place.SearchAddPlaceViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchAddPlaceActivity :
    BaseActivity<ActivitySearchAddPlaceBinding>(ActivitySearchAddPlaceBinding::inflate) {

    private val viewModel by viewModels<SearchAddPlaceViewModel>()
    private val searchAddPlaceAdapter = SearchAddPlaceAdapter(
        clickAddPlaceListener = { placeId ->
            viewModel.onAction(SearchAddPlaceAction.ClickAddPlace(placeId))
        },
        clickShowDetailListener = { placeId ->
            viewModel.onAction(SearchAddPlaceAction.ClickSeeDetail(placeId))
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        collectState()
        collectSideEffect()
    }

    private fun initView() {
        binding.rvPlace.apply {
            adapter = searchAddPlaceAdapter
            layoutManager = LinearLayoutManager(context)
            itemAnimator = null
        }

        binding.etSearch.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                viewModel.onAction(SearchAddPlaceAction.SearchKeyword(binding.etSearch.text.toString()))
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
    }

    private fun collectState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.container.stateFlow
                    .map { it.places }
                    .collect {
                        searchAddPlaceAdapter.submitList(it)
                    }
            }
        }
    }

    private fun collectSideEffect() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.container.sideEffectFlow.collect {
                    when (it) {
                        SearchAddPlaceMutation.SideEffect.Finish -> {
                            finish()
                        }
                        is SearchAddPlaceMutation.SideEffect.GoDetail -> {
                            startActivity(PlaceDetailActivity.getIntent(this@SearchAddPlaceActivity, it.placeId))
                        }
                        SearchAddPlaceMutation.SideEffect.HideLoading -> {
//                            TODO()
                        }
                        is SearchAddPlaceMutation.SideEffect.SendPlaceInfo -> {
                            val intent = Intent()
                            intent.putExtra(Constant.PLACE_INFO, it.place.mapToDto())
                            setResult(RESULT_OK, intent)
                            finish()
                        }
                        SearchAddPlaceMutation.SideEffect.ShowLoading -> {
//                            TODO()
                        }
                        is SearchAddPlaceMutation.SideEffect.ShowSnackBar -> {
                            Snackbar
                                .make(binding.root, it.message, Snackbar.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            }
        }
    }

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, SearchAddPlaceActivity::class.java)
        }
    }
}
