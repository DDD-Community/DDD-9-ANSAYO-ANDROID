package com.ddd.ansayo.search

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResultListener
import com.ddd.ansayo.base.BaseFragment
import com.ddd.ansayo.databinding.FragmentSearchListBinding
import com.ddd.ansayo.presentation.viewmodel.Constant
import com.google.android.material.tabs.TabLayoutMediator

class SearchListFragment :
    BaseFragment<FragmentSearchListBinding>(FragmentSearchListBinding::inflate){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        setFragmentResultListener()
    }

    private fun initView() {
        binding.vpSearch.adapter = SearchListAdapter(this)

        val tabs = arrayOf("코스","장소")
        TabLayoutMediator(binding.tabLayout, binding.vpSearch) {tab, pos ->
            tab.text = tabs[pos]
        }.attach()
    }
    private fun setFragmentResultListener() {
        setFragmentResultListener(Constant.SELECT_SEARCH_TAB) {_, _ ->
            childFragmentManager.setFragmentResult(Constant.FETCH_SEARCH_COURSE, bundleOf())
            childFragmentManager.setFragmentResult(Constant.FETCH_FAVORITE_PLACE, bundleOf())
        }
    }
}