package com.ddd.ansayo.main.favorite

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.ddd.ansayo.R
import com.ddd.ansayo.base.BaseFragment
import com.ddd.ansayo.core_design.util.DividerDecoration
import com.ddd.ansayo.databinding.FragmentFavoritePlaceBinding
import com.ddd.ansayo.domain.model.favorite.FavoritePlaceAction
import com.ddd.ansayo.domain.model.favorite.FavoritePlaceMutation
import com.ddd.ansayo.extenstion.setHtmlText
import com.ddd.ansayo.presentation.viewmodel.Constant
import com.ddd.ansayo.presentation.viewmodel.favorite.FavoritePlaceViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoritePlaceFragment :
    BaseFragment<FragmentFavoritePlaceBinding>(FragmentFavoritePlaceBinding::inflate) {

    private val viewModel by viewModels<FavoritePlaceViewModel>()
    private val favoriteAdapter = FavoritePlaceAdapter(
        placeClickListener = {
            viewModel.onAction(FavoritePlaceAction.ClickPlace(it))
        },
        favoriteClickListener = { id, like ->
            viewModel.onAction(FavoritePlaceAction.ClickFavorite(id, like))
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
        binding.tvNoFavoriteTitle.setHtmlText(getString(R.string.no_favorite_place_title))

        binding.btnFindPlace.setOnClickListener {
            viewModel.onAction(FavoritePlaceAction.ClickFindPlace)
        }

        binding.rvFavoritePlace.apply {
            adapter = favoriteAdapter
            layoutManager = LinearLayoutManager(context)
            itemAnimator = null
            setHasFixedSize(true)
            addItemDecoration(DividerDecoration(context, 16))
        }
    }

    private fun setFragmentResultListener() {
        setFragmentResultListener(Constant.FETCH_FAVORITE_PLACE) { _, _ ->
            viewModel.onAction(FavoritePlaceAction.SelectFavoriteListTab)
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
                            favoriteAdapter.submitList(it)
                        }
                }

                launch {
                    viewModel.container.stateFlow
                        .map { it.hasFavorites }
                        .distinctUntilChanged()
                        .collect {
                            binding.layoutNoFavorites.isVisible = it.not()
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
                        is FavoritePlaceMutation.SideEffect.NaviToPlaceDetail -> {

                        }

                        FavoritePlaceMutation.SideEffect.NaviToSearch -> {

                        }

                        is FavoritePlaceMutation.SideEffect.ShowSnackBar -> {
                            Snackbar.make(binding.root, it.message, Snackbar.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }
}
