package com.ddd.ansayo.main.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.ddd.ansayo.base.BaseFragment
import com.ddd.ansayo.course.CourseCreateActivity
import com.ddd.ansayo.databinding.FragmentHomeBinding
import com.ddd.ansayo.domain.model.home.HomeAction
import com.ddd.ansayo.domain.model.home.HomeMutation
import com.ddd.ansayo.search.SearchActivity
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel by viewModels<HomeViewModel>()
    private val badgeAdapter = SearchableBadgeAdapter(
        badgeClickListener = { badge ->
            viewModel.onAction(HomeAction.ClickBadgeCategory(badge))
        }
    )
    private val popularCourseAdapter = PopularCourseAdapter(
        courseClickListener = {courseId ->
            viewModel.onAction(HomeAction.ClickBadgeCourse(courseId))
        }
    )
    private val recommendCourseAdapter = RecommendCourseAdapter(
        courseClickListener = {courseId ->
            viewModel.onAction(HomeAction.ClickBadgeCourse(courseId))
        }
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        collectState()
        collectSideEffect()
    }
    private fun initView() {
        viewModel.onAction(HomeAction.SearchableBadgeList)
        viewModel.onAction(HomeAction.GetRecommandCourseBadge)

        binding.apply {
            rvCategoryBadge.adapter = badgeAdapter
            rvCourse.adapter = popularCourseAdapter
            rvRecommedCourse.adapter = recommendCourseAdapter
            btnCourseWrite.setOnClickListener {
                viewModel.onAction(HomeAction.ClickRecordButton)
            }
            btnSearch.setOnClickListener {
                viewModel.onAction(HomeAction.ClickSearchButton)

            }
        }
    }
    private fun collectState() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                launch {
                    viewModel.container.stateFlow
                        .map { it }
                        .distinctUntilChanged()
                        .collect {
                            badgeAdapter.submitList(it.badge)
                            if (!it.badge.isNullOrEmpty()) {
                                viewModel.onAction(HomeAction.GetBasicPopularCourse(it.badge.first().id))
                            }
                        }
                }
                launch {
                    viewModel.container.stateFlow
                        .map { it }
                        .distinctUntilChanged()
                        .collect {
                            popularCourseAdapter.submitList(it.badgeCourse)
                        }
                }
                launch {
                    viewModel.container.stateFlow
                        .map { it }
                        .distinctUntilChanged()
                        .collect {
                            recommendCourseAdapter.submitList(it.recommandCourse)
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
                        is HomeMutation.SideEffect.ClickBadge -> {
                            badgeAdapter.setSelectedBadge(it.badge)
                        }
                        is HomeMutation.SideEffect.NaviToCourseDetail -> {

                        }
                        is HomeMutation.SideEffect.ShowSnackBar -> {
                            Snackbar.make(binding.root, it.message, Snackbar.LENGTH_SHORT).show()
                        }
                        is HomeMutation.SideEffect.NaviToRecord -> {
                            activity?.let {
                                startActivity(Intent(it, CourseCreateActivity::class.java))
                            }
                        }
                        is HomeMutation.SideEffect.NaviToSearch -> {
                            activity?.let {
                                startActivity(Intent(it, SearchActivity::class.java))
                            }

                        }
                    }
                }
            }
        }
    }

}
