package com.ddd.ansayo.course.info

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.ddd.ansayo.base.BaseActivity
import com.ddd.ansayo.databinding.ActivityCourseInfoBinding
import com.ddd.ansayo.domain.model.course.info.CourseInfoAction
import com.ddd.ansayo.domain.model.course.info.CourseInfoMutation
import com.ddd.ansayo.presentation.viewmodel.course.CourseInfoViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CourseInfoActivity :
    BaseActivity<ActivityCourseInfoBinding>(ActivityCourseInfoBinding::inflate){

    private val viewModel by viewModels<CourseInfoViewModel>()
    private val courseInfoAdapter = CourseInfoAdapter(
        placeClickListener = {placeId ->
            viewModel.onAction(CourseInfoAction.NaviToPlaceDetail(placeId))
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        collectState()
        collectSideEffect()
    }

    private fun initView() {
        binding.rvCoursePlave.apply {
            adapter = courseInfoAdapter
        }
    }
    private fun collectState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.container.stateFlow
                        .map { it.courseInfo.places }
                        .distinctUntilChanged()
                        .collect {
                            courseInfoAdapter.submitList(it)
                        }

                }
            }
        }
    }

    private fun collectSideEffect() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.container.sideEffectFlow.collect {
                    when(it) {
                        CourseInfoMutation.SideEffect.BackScreen -> {
                            finish()
                        }
                        is CourseInfoMutation.SideEffect.NaviToPlaceDetail -> {

                        }
                    }
                }
            }

        }

    }


}