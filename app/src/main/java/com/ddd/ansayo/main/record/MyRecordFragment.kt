package com.ddd.ansayo.main.record

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.ddd.ansayo.base.BaseFragment
import com.ddd.ansayo.course.CourseCreateActivity
import com.ddd.ansayo.course.info.CourseInfoActivity
import com.ddd.ansayo.databinding.FragmentMyRecordBinding
import com.ddd.ansayo.domain.model.favorite.FavoritePlaceAction
import com.ddd.ansayo.domain.model.record.MyRecordAction
import com.ddd.ansayo.domain.model.record.MyRecordMutation
import com.ddd.ansayo.presentation.viewmodel.Constant
import com.ddd.ansayo.presentation.viewmodel.record.MyRecordViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MyRecordFragment : BaseFragment<FragmentMyRecordBinding>(FragmentMyRecordBinding::inflate) {

    private val viewModel by viewModels<MyRecordViewModel>()
    private val myRecordAdapter = MyRecordAdapter(
        courseClickListener = {
            viewModel.onAction(MyRecordAction.ClickCourse(it.id))
        }
    )
    private val createCourseLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == Activity.RESULT_OK) {
            viewModel.onAction(MyRecordAction.CompleteCourseWrite)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        collectState()
        setFragmentResultListener()
        collectSideEffect()
    }

    private fun initView() {
        binding.rvMyCourse.apply {
            adapter = myRecordAdapter
            layoutManager = LinearLayoutManager(context)
            itemAnimator = null
            setHasFixedSize(true)
        }

        binding.ivWrite.setOnClickListener {
            viewModel.onAction(MyRecordAction.ClickCourseWriteShortCutButton)
        }

        binding.btnCourseWrite.setOnClickListener {
            viewModel.onAction(MyRecordAction.ClickCourseWriteButton)
        }
    }

    private fun setFragmentResultListener() {
        setFragmentResultListener(Constant.SELECT_MY_RECORD_TAB) { _, _ ->
            viewModel.onAction(MyRecordAction.SelectMyRecordTab)
        }
    }

    private fun collectState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                launch {
                    viewModel.container.stateFlow
                        .map { it.hasRecord }
                        .distinctUntilChanged()
                        .collect {
                            binding.clNoRecord.isVisible = it.not()
                        }
                }

                launch {
                    viewModel.container.stateFlow
                        .map { it.courses }
                        .distinctUntilChanged()
                        .collect {
                            myRecordAdapter.submitList(it)
                        }
                }
            }
        }
    }

    private fun collectSideEffect() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.container.sideEffectFlow.collect {
                    when (it) {
                        MyRecordMutation.SideEffect.NaviToCourseWrite -> {
                            createCourseLauncher.launch(CourseCreateActivity.getIntent(requireContext()))
                        }
                        is MyRecordMutation.SideEffect.NaviToCourseDetail -> {
                            startActivity(CourseInfoActivity.getIntent(requireContext(), it.id))
                        }
                    }
                }
            }
        }
    }
}
