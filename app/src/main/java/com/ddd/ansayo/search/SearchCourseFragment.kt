package com.ddd.ansayo.search

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.ddd.ansayo.R
import com.ddd.ansayo.base.BaseFragment
import com.ddd.ansayo.course.info.CourseInfoActivity
import com.ddd.ansayo.databinding.FragmentSearchCourseBinding
import com.ddd.ansayo.domain.model.search.SearchCourseAction
import com.ddd.ansayo.domain.model.search.SearchCourseMutation
import com.ddd.ansayo.presentation.viewmodel.Constant
import com.ddd.ansayo.presentation.viewmodel.course.CourseSearchViewModel
import com.google.android.material.snackbar.Snackbar
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
    private var searchKeyword: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            searchKeyword = it.getString("searchKeyword")
            viewModel.onAction(SearchCourseAction.SearchKeyword(searchKeyword!!))
        }

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
        binding.btnRecordCourse.setOnClickListener {
            viewModel.onAction(SearchCourseAction.ClickRecordCourse)
        }
        val messageTemplate = getString(R.string.search_result_not_found_course)
        val startIndex = messageTemplate.indexOf("%s")
        val keywordColorSpan = ForegroundColorSpan(ContextCompat.getColor(requireContext(), com.ddd.ansayo.core_design.R.color.orange_point))
        val message = SpannableStringBuilder(messageTemplate.replace("%s", searchKeyword!!))
        message.setSpan(keywordColorSpan, startIndex, startIndex + searchKeyword!!.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.tvTitle.text = message

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
                            binding.clEmptyResult.isVisible = it.isEmpty()
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
                        SearchCourseMutation.SideEffect.NavToPlace -> {

                        }
                        is SearchCourseMutation.SideEffect.StartCourseDetail -> {
                           val courseId = it.id
                            activity?.let {
                              startActivity(CourseInfoActivity.getIntent(this@SearchCourseFragment.requireContext(), courseId))
                            }
                        }
                        SearchCourseMutation.SideEffect.StartCourseRecord -> {
                        }
                        SearchCourseMutation.SideEffect.BackScreen -> {
                            parentFragmentManager.popBackStack()

                        }
                        SearchCourseMutation.SideEffect.SerachScreen -> {
                            parentFragmentManager.popBackStack()
                        }
                        is SearchCourseMutation.SideEffect.ShowSnackBar -> {
                            Snackbar.make(binding.root, it.message, Snackbar.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

}