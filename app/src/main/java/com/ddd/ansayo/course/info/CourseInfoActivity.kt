package com.ddd.ansayo.course.info

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.ddd.ansayo.R
import com.ddd.ansayo.base.BaseActivity
import com.ddd.ansayo.core_model.course.CourseInfo
import com.ddd.ansayo.core_model.place.Geometry
import com.ddd.ansayo.core_model.place.Place
import com.ddd.ansayo.course.info.CourseMapBindingAdapter.setMultiGeoPoint
import com.ddd.ansayo.data.AuthLocalDataSource
import com.ddd.ansayo.databinding.ActivityCourseInfoBinding
import com.ddd.ansayo.domain.model.course.info.CourseInfoAction
import com.ddd.ansayo.domain.model.course.info.CourseInfoMutation
import com.ddd.ansayo.place.PlaceDetailActivity
import com.ddd.ansayo.presentation.viewmodel.Constant
import com.ddd.ansayo.presentation.viewmodel.course.CourseInfoViewModel
import com.google.android.material.snackbar.Snackbar
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.LocationTrackingMode
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.abs


@AndroidEntryPoint
class CourseInfoActivity :
    BaseActivity<ActivityCourseInfoBinding>(ActivityCourseInfoBinding::inflate) {

    private val viewModel by viewModels<CourseInfoViewModel>()
    private val headerAdapter by lazy {
        CourseInfoAdapter(
            favoriteClickListener = {
                viewModel.onAction(CourseInfoAction.ClickFavorite(it))
            }
        )
    }
    private val courseInfoAdapter by lazy {
        CourseDetailInfoAdapter(
            placeClickListener = {
                viewModel.onAction(CourseInfoAction.ClickPlace(it))
            }
        )
    }
    private val tempCourse = CourseInfo.TEMPDATA
    lateinit var mapFragment: MapFragment
    lateinit var naverMap: NaverMap

    @Inject
    lateinit var authLocalDataSource: AuthLocalDataSource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        collectState()
        collectSideEffect()

        val courseId = intent.getStringExtra(Constant.COURSE_ID).orEmpty()
        viewModel.onAction(CourseInfoAction.EnteredScreen(courseId))
    }
    private fun initView() {
        binding.run {
            appBar.addOnOffsetChangedListener { appBar, verticalOffset ->
                val threshold = layoutTitle.height / 2
                val alpha = when {
                    abs(verticalOffset) == appBar.totalScrollRange -> 1f
                    abs(verticalOffset) >= appBar.totalScrollRange - threshold -> {
                        1 - (appBar.totalScrollRange - abs(verticalOffset)) / threshold.toFloat()
                    }
                    else -> 0f
                }
               layoutTitle.alpha = alpha
            }
            rvCouseDetail.apply {
                adapter = ConcatAdapter(headerAdapter,courseInfoAdapter)
                layoutManager = LinearLayoutManager(context)
                itemAnimator = null
            }

            val lat = tempCourse.places.first().geometry.lat
            val lng = tempCourse.places.first().geometry.lng

            mapFragment = createNaverMapFragment(LatLng(lat, lng))
            supportFragmentManager.beginTransaction()
                .replace(R.id.fcv_map_container, mapFragment)
                .commitAllowingStateLoss()
        }

    }
    private fun createNaverMapFragment(courseLocation: LatLng): MapFragment {
        return MapFragment.newInstance().apply {
            getMapAsync {
                it.minZoom = 11.5
                it.uiSettings.apply {
                    isCompassEnabled = false
                    isZoomControlEnabled = false
                    isLocationButtonEnabled = false
                }
                it.locationTrackingMode = LocationTrackingMode.None

                naverMap = it
                naverMap.moveCamera(
                    CameraUpdate.scrollTo(
                        LatLng(courseLocation.latitude, courseLocation.longitude)
                    )
                )

            }
            tempCourse.let { setMultiGeoPoint(it.places, viewModel) }
        }
    }

    private fun collectState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.container.stateFlow
                        .map { it.courseInfo }
                        .distinctUntilChanged()
                        .collect {
                            if (it == null) return@collect
                            headerAdapter.onChanged(it)
                        }
                }
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
                    when (it) {
                        CourseInfoMutation.SideEffect.BackScreen -> {
                            finish()
                        }

                        is CourseInfoMutation.SideEffect.NaviToPlaceDetail -> {
                            startActivity(
                                PlaceDetailActivity.getIntent(this@CourseInfoActivity, it.placeId)
                            )

                        }

                        is CourseInfoMutation.SideEffect.ShowSnackBar -> {
                            Snackbar.make(binding.root, it.message, Snackbar.LENGTH_SHORT).show()
                        }
                    }
                }
            }

        }
    }

    companion object {
        fun getIntent(context: Context, courseId: String): Intent {
            return Intent(context, CourseInfoActivity::class.java).apply {
                putExtra(Constant.COURSE_ID, courseId)
            }
        }
    }
}