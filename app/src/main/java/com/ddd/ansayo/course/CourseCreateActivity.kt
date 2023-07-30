package com.ddd.ansayo.course

import android.Manifest
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.ddd.ansayo.R
import com.ddd.ansayo.base.BaseActivity
import com.ddd.ansayo.databinding.ActivityCourseCreateBinding
import com.ddd.ansayo.domain.model.course.CourseWriteAction
import com.ddd.ansayo.domain.model.course.CourseWriteMutation
import com.ddd.ansayo.presentation.viewmodel.course.CourseCreateViewModel
import com.esafirm.imagepicker.features.ImagePickerConfig
import com.esafirm.imagepicker.features.ImagePickerLauncher
import com.esafirm.imagepicker.features.registerImagePicker
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.coroutine.TedPermission
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import com.ddd.ansayo.core_design.R as coreDesignR

@AndroidEntryPoint
class CourseCreateActivity :
    BaseActivity<ActivityCourseCreateBinding>(ActivityCourseCreateBinding::inflate) {

    private val viewModel by viewModels<CourseCreateViewModel>()
    private val headerAdapter = CourseWriteHeaderAdapter(
        courseTitleChangedListener = {
            viewModel.onAction(CourseWriteAction.InputCourseTitle(it))
        },
        courseDescriptionChangedListener = {
            viewModel.onAction(CourseWriteAction.InputCourseDescription(it))
        },
        dateClickListener = {
            viewModel.onAction(CourseWriteAction.ClickDatePicker)
        }
    )
    private val placeAdapter = CourseWritePlaceAdapter(
        placeReviewChangedListener = { order, text ->
            viewModel.onAction(CourseWriteAction.InputPlaceReview(order, text))
        },
        placeDeleteClickListener = { order ->
            viewModel.onAction(CourseWriteAction.ClickDeletePlace(order))
        },
        placeAddClickListener = {
            viewModel.onAction(CourseWriteAction.ClickAddPlace)
        },
        placeImageAddClickListener = { order ->
            viewModel.onAction(CourseWriteAction.ClickAddPlaceImage(order))
        },
        placeImageDeleteClickListener = { order, imageIndex ->
            viewModel.onAction(CourseWriteAction.ClickDeletePlaceImage(order, imageIndex))
        },

    )
    private val footerAdapter = CourseWriteFooterAdapter(
        visibilityChangedListener = {
            viewModel.onAction(CourseWriteAction.ToggleVisibilitySwitch(it))
        },
        completeClickListener = {
            viewModel.onAction(CourseWriteAction.ClickUploadButton)
        }
    )

    val imagePickerLauncher: ((Int) -> ImagePickerLauncher) = { placeOrder ->
        registerImagePicker { images ->
            viewModel.onAction(
                CourseWriteAction.SelectImages(
                    placeOrder = placeOrder,
                    images = images.map { it.uri.toString() }
                )
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        collectState()
        collectSideEffect()
    }

    private fun initView() {
        binding.rvPlace.apply {
            adapter = ConcatAdapter(headerAdapter, placeAdapter, footerAdapter)
            layoutManager = LinearLayoutManager(context)
            itemAnimator = null
        }
    }

    private fun collectState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.container.stateFlow
                        .map { it.header }
                        .distinctUntilChanged()
                        .collect {
                            headerAdapter.onChanged(it)
                        }

                    viewModel.container.stateFlow
                        .map { it.places }
                        .distinctUntilChanged()
                        .collect {
                            placeAdapter.submitList(it)
                        }

                    viewModel.container.stateFlow
                        .map { it.footer }
                        .distinctUntilChanged()
                        .collect {
                            footerAdapter.onChanged(it)
                        }
                }
            }
        }
    }

    private fun collectSideEffect() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.container.sideEffectFlow.collect {
                    when (it) {
                        CourseWriteMutation.SideEffect.Finish -> {
                            finish()
                        }

                        CourseWriteMutation.SideEffect.GoToAddPlace -> {
                            TODO()
                        }

                        CourseWriteMutation.SideEffect.HideLoading -> {
                            TODO()
                        }

                        CourseWriteMutation.SideEffect.ShowDatePickerDialog -> {
                            MaterialDatePicker.Builder
                                .datePicker()
                                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                                .build()
                                .apply {
                                    addOnPositiveButtonClickListener { time ->
                                        val date = SimpleDateFormat("yyyy년 MM월 dd일", Locale.KOREA).format(Date(time))
                                        viewModel.onAction(CourseWriteAction.SelectDate(date))
                                    }
                                }
                                .show(supportFragmentManager, "")
                        }

                        CourseWriteMutation.SideEffect.ShowLoading -> {
                            TODO()
                        }

                        is CourseWriteMutation.SideEffect.ShowPhotoPicker -> {
                            TedPermission.create()
                                .setPermissions(
                                    Manifest.permission.CAMERA,
                                    Manifest.permission.READ_EXTERNAL_STORAGE,
                                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                                )
                                .setPermissionListener(object : PermissionListener {
                                    override fun onPermissionGranted() {
                                        imagePickerLauncher.invoke(it.order).launch(
                                            ImagePickerConfig {
                                                language = "ko"
                                                theme = R.style.Theme_DDD9ANSAYOANDROID
                                                arrowColor = getColor(coreDesignR.color.black)
                                                folderTitle = "폴더 선택"
                                                imageTitle = "이미지 선택"
                                                doneButtonText = "완료"
                                                limit = it.remainCount
                                                isShowCamera = true
                                            }
                                        )
                                    }

                                    override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
                                        Snackbar
                                            .make(binding.root, "", Snackbar.LENGTH_SHORT)
                                            .show()
                                    }
                                })
                                .check()
                        }

                        is CourseWriteMutation.SideEffect.ShowSnackBar -> {
                            Snackbar
                                .make(binding.root, it.message, Snackbar.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            }
        }
    }
}
