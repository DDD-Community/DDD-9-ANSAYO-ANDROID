package com.ddd.ansayo.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.ddd.ansayo.base.BaseActivity
import com.ddd.ansayo.databinding.ActivitySearchListBinding
import com.ddd.ansayo.presentation.viewmodel.Constant
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
            val searchKeyword = intent.getStringExtra(Constant.SEARCH_WORD).orEmpty()
            vSearch.setText(searchKeyword)
            vpSearch.adapter = SearchListAdapter(this@SearchListActivity,searchKeyword)
            vSearch.apply {
                setFocusCallback {
                    finish()
                }
            }
        }
        val tabs = arrayOf("코스","장소")
        TabLayoutMediator(binding.tabLayout, binding.vpSearch) {tab, pos ->
            tab.text = tabs[pos]
        }.attach()
    }
    companion object {
        fun getIntent(context: Context, keyword: String): Intent {
            return Intent(context, SearchListActivity::class.java).apply {
                putExtra(Constant.SEARCH_WORD, keyword)
            }
        }
    }
}