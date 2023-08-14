package com.ddd.ansayo.main.favorite

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResultListener
import com.ddd.ansayo.base.BaseFragment
import com.ddd.ansayo.databinding.FragmentFavoriteListBinding
import com.ddd.ansayo.presentation.viewmodel.Constant
import com.google.android.material.tabs.TabLayoutMediator
import com.orhanobut.logger.Logger

class FavoriteListFragment :
    BaseFragment<FragmentFavoriteListBinding>(FragmentFavoriteListBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        setFragmentResultListener()
    }

    private fun initView() {
        binding.vpFavorite.adapter = FavoriteListFragmentAdapter(this)

        val tabs = arrayOf("코스", "장소")
        TabLayoutMediator(binding.tabLayout, binding.vpFavorite) { tab, position ->
            tab.text = tabs[position]
        }.attach()
    }

    private fun setFragmentResultListener() {
        setFragmentResultListener(Constant.SELECT_FAVORITE_TAB) { _, _ ->
            childFragmentManager.setFragmentResult(Constant.FETCH_FAVORITE_COURSE, bundleOf())
            childFragmentManager.setFragmentResult(Constant.FETCH_FAVORITE_PLACE, bundleOf())
        }
    }
}
