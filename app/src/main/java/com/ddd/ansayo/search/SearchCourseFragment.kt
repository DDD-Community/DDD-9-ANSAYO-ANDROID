package com.ddd.ansayo.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.ddd.ansayo.base.BaseFragment
import com.ddd.ansayo.databinding.FragmentSearchCourseBinding
import com.ddd.ansayo.domain.model.search.SearchCourseAction
import com.ddd.ansayo.domain.model.search.SearchCourseMutation
import com.ddd.ansayo.presentation.viewmodel.Constant
import com.ddd.ansayo.presentation.viewmodel.course.CourseSearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchCourseFragment :
    BaseFragment<FragmentSearchCourseBinding>(FragmentSearchCourseBinding::inflate) {

    private val viewModel by viewModels<CourseSearchViewModel>()
    private val searchAdapter = SearchCourseAdapter(
        courseClickListener = {
            viewModel.onAction(SearchCourseAction.ClickCourseList(it))
        }
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        setFragmentResultListener()
        collectState()
        collectSideEffect()
    }

    private fun initView() {
        binding.rvSearchCourse.run {
            adapter = searchAdapter
            itemAnimator = null
            setHasFixedSize(true)
        }
    }

    private fun setFragmentResultListener() {
        setFragmentResultListener(Constant.FETCH_SEARCH_COURSE) { _, _ ->
            viewModel.onAction(SearchCourseAction.SelectSearchListTab)
        }
    }

    private fun collectState() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                launch {
                    viewModel.container.stateFlow
                        .map { it.courses }
                        .distinctUntilChanged()
                        .collect{
                            searchAdapter.submitList(it)
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
                        is SearchCourseMutation.SideEffect.NavToPlace -> {

                        }

                        is SearchCourseMutation.SideEffect.StartCourseRecord -> {

                        }
                        is SearchCourseMutation.SideEffect.StartCourseDetail -> {

                        }
                        is SearchCourseMutation.SideEffect.BackScreen -> {

                        }
                        else -> {

                        }
                    }
                }
            }
        }
    }

}