package com.ddd.ansayo.course

import android.Manifest
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.ddd.ansayo.R
import com.ddd.ansayo.base.BaseActivity
import com.ddd.ansayo.core_model.place.AddPlaceInfoDto
import com.ddd.ansayo.databinding.ActivityCourseCreateBinding
import com.ddd.ansayo.domain.model.course.CourseWriteAction
import com.ddd.ansayo.domain.model.course.CourseWriteMutation
import com.ddd.ansayo.place.SearchAddPlaceActivity
import com.ddd.ansayo.presentation.viewmodel.Constant
import com.ddd.ansayo.presentation.viewmodel.course.CourseCreateViewModel
import com.ddd.ansayo.util.PermissionCompatHelper
import com.esafirm.imagepicker.features.ImagePickerConfig
import com.esafirm.imagepicker.features.ImagePickerLauncher
import com.esafirm.imagepicker.features.registerImagePicker
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.coroutine.TedPermission
import com.orhanobut.logger.Logger
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
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

    private var imagePickerOrder = 0
    private val imagePickerLauncher = registerImagePicker { images ->
        images.forEachIndexed { index, image ->
            Logger.d("image$index \n" +
                    "id : ${image.id}\n" +
                    "name : ${image.name}\n" +
                    "path : ${image.path}\n" +
                    "uri : ${image.uri}")
        }
        viewModel.onAction(
            CourseWriteAction.SelectImages(
                placeOrder = imagePickerOrder,
                images = images.map { it.uri.toString() }
            )
        )
    }

    private val searchAddPlaceLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode != RESULT_OK) return@registerForActivityResult

        (it.data?.getSerializableExtra(Constant.PLACE_INFO) as? AddPlaceInfoDto)?.let { placeInfo ->
            viewModel.onAction(CourseWriteAction.SelectPlace(placeInfo))
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
        viewModel.container.stateFlow
            .flowWithLifecycle(lifecycle)
            .map { it.header }
            .distinctUntilChanged()
            .onEach {
                headerAdapter.onChanged(it)
            }
            .launchIn(lifecycleScope)

        viewModel.container.stateFlow
            .flowWithLifecycle(lifecycle)
            .map { it.places }
            .distinctUntilChanged()
            .onEach {
                placeAdapter.submitList(it)
            }
            .launchIn(lifecycleScope)

        viewModel.container.stateFlow
            .flowWithLifecycle(lifecycle)
            .map { it.footer }
            .distinctUntilChanged()
            .onEach {
                footerAdapter.onChanged(it)
            }
            .launchIn(lifecycleScope)
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
                            searchAddPlaceLauncher.launch(SearchAddPlaceActivity.getIntent(this@CourseCreateActivity))
                        }

                        CourseWriteMutation.SideEffect.HideLoading -> {
//                            TODO()
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
//                            TODO()
                        }

                        is CourseWriteMutation.SideEffect.ShowPhotoPicker -> {
                            val permissionResult = TedPermission.create()
                                .setPermissions(*PermissionCompatHelper.imagePermission)
                                .check()

                            if (permissionResult.isGranted) {
                                imagePickerOrder = it.order
                                imagePickerLauncher.launch(
                                    ImagePickerConfig {
                                        language = "ko"
                                        theme = R.style.Theme_DDD9ANSAYOANDROID
                                        arrowColor = getColor(coreDesignR.color.N0)
                                        folderTitle = "폴더 선택"
                                        imageTitle = "이미지 선택"
                                        doneButtonText = "완료"
                                        limit = it.remainCount
                                        isShowCamera = true
                                    }
                                )
                            } else {
                                Snackbar.make(binding.root, permissionResult.deniedPermissions.toString(), Snackbar.LENGTH_SHORT).show()
                            }

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

    companion object {
        fun getIntent(context: Context) : Intent {
            return Intent(context, CourseCreateActivity::class.java)
        }
    }
}
