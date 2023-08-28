package com.ddd.ansayo.search

import android.os.Bundle
import com.ddd.ansayo.base.BaseActivity
import com.ddd.ansayo.databinding.ActivitySearchListBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchListActivity :
    BaseActivity<ActivitySearchListBinding>(ActivitySearchListBinding::inflate){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()

    }

    private fun initView() {
        binding.run {
            vpSearch.adapter = SearchListAdapter(this@SearchListActivity)
        }
        val tabs = arrayOf("코스","장소")
        TabLayoutMediator(binding.tabLayout, binding.vpSearch) {tab, pos ->
            tab.text = tabs[pos]
        }.attach()
    }

}