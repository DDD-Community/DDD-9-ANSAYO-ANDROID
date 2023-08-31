package com.ddd.ansayo.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.ddd.ansayo.base.BaseFragment
import com.ddd.ansayo.databinding.FragmentSearchPlaceBinding
import com.ddd.ansayo.domain.model.search.SearchPlaceAction
import com.ddd.ansayo.domain.model.search.SearchPlaceMutation
import com.ddd.ansayo.presentation.viewmodel.Constant
import com.ddd.ansayo.presentation.viewmodel.place.SearchPlaceViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch


@AndroidEntryPoint
class SearchPlaceFragment:
    BaseFragment<FragmentSearchPlaceBinding>(FragmentSearchPlaceBinding::inflate) {
    private val viewModel by viewModels<SearchPlaceViewModel>()
    private val placeAdapter = SearchPlaceAdapter(
        placeClickListener = {
            viewModel.onAction(SearchPlaceAction.ClickPlaceList(it))
        }
    )
    private var searchKeyword: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            searchKeyword = it.getString("searchKeyword")
            viewModel.onAction(SearchPlaceAction.SearchKeyword(searchKeyword!!))
        }

        initView()
        setFragmentResultListener()
        collectState()
        collectSideEffect()

    }
    private fun initView() {
        binding.rvSearchPlace.run {
            adapter = placeAdapter
            itemAnimator = null
            setHasFixedSize(true)
        }
    }

    private fun setFragmentResultListener() {
        setFragmentResultListener(Constant.FETCH_SEARCH_PLACE) { _, _ ->
            viewModel.onAction(SearchPlaceAction.SelectSearchListTab)
        }
    }

    private fun collectState() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                launch {
                    viewModel.container.stateFlow
                        .map { it.places }
                        .distinctUntilChanged()
                        .collect {
                            placeAdapter.submitList(it)
                        }
                }
            }
        }
    }

    private fun collectSideEffect() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.container.sideEffectFlow.collect {
                    when (it) {
                        SearchPlaceMutation.SideEffect.BackScreen -> {
                            parentFragmentManager.popBackStack()
                        }

                        SearchPlaceMutation.SideEffect.NavToCourse -> {

                        }

                        is SearchPlaceMutation.SideEffect.StartPlaceDetail -> {

                        }

                        is SearchPlaceMutation.SideEffect.ShowSnackBar -> {
                            Snackbar.make(binding.root, it.message, Snackbar.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }
}