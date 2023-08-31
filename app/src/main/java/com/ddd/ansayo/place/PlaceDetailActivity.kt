package com.ddd.ansayo.place

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.ddd.ansayo.base.BaseActivity
import com.ddd.ansayo.databinding.ActivityPlaceDetailBinding
import com.ddd.ansayo.domain.model.place.PlaceDetailAction
import com.ddd.ansayo.domain.model.place.PlaceDetailMutation
import com.ddd.ansayo.presentation.viewmodel.Constant
import com.ddd.ansayo.presentation.viewmodel.place.PlaceDetailViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlin.math.abs

@AndroidEntryPoint
class PlaceDetailActivity :
    BaseActivity<ActivityPlaceDetailBinding>(ActivityPlaceDetailBinding::inflate) {

    private val viewModel by viewModels<PlaceDetailViewModel>()
    private val headerAdapter by lazy {
        PlaceDetailInfoAdapter(
            favoriteClickListener = {
                viewModel.onAction(PlaceDetailAction.ClickFavorite(it))
            }
        )
    }
    private val courseAdapter by lazy {
        PlaceDetailCourseAdapter(
            courseClickListener = {
                viewModel.onAction(PlaceDetailAction.ClickCourse(it.id))
            }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        collectState()
        collectSideEffect()

        val placeId = intent.getStringExtra(Constant.PLACE_ID).orEmpty()
        viewModel.onAction(PlaceDetailAction.EnteredScreen(placeId))
    }

    private fun initView() {
        binding.appBar.addOnOffsetChangedListener { appBar, verticalOffset ->
            val threshold = binding.layoutTitle.height / 2
            val alpha = when {
                abs(verticalOffset) == appBar.totalScrollRange -> 1f
                abs(verticalOffset) >= appBar.totalScrollRange - threshold -> {
                    1 - (appBar.totalScrollRange - abs(verticalOffset)) / threshold.toFloat()
                }
                else -> 0f
            }
            binding.layoutTitle.alpha = alpha
        }

        binding.rvPlaceDetail.apply {
            adapter = ConcatAdapter(headerAdapter, courseAdapter)
            layoutManager = LinearLayoutManager(context)
            itemAnimator = null
        }
    }

    private fun collectState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.container.stateFlow
                        .map { it.placeDetail }
                        .distinctUntilChanged()
                        .collect {
                            if (it == null) return@collect
                            Glide.with(this@PlaceDetailActivity)
                                .load(it.place.photos?.firstOrNull())
                                .into(binding.ivPlace)
                            binding.tvTitle.text = it.place.name
                            headerAdapter.onChanged(it)
                        }
                }

                launch {
                    viewModel.container.stateFlow
                        .map { it.courses }
                        .distinctUntilChanged()
                        .collect {
                            courseAdapter.submitList(it)
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
                        PlaceDetailMutation.SideEffect.Finish -> {
                            finish()
                        }

                        is PlaceDetailMutation.SideEffect.NaviToCourseDetail -> {

                        }

                        is PlaceDetailMutation.SideEffect.ShowSnackBar -> {
                            Snackbar.make(binding.root, it.message, Snackbar.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    companion object {
        fun getIntent(context: Context, placeId: String): Intent {
            return Intent(context, PlaceDetailActivity::class.java).apply {
                putExtra(Constant.PLACE_ID, placeId)
            }
        }
    }
}
