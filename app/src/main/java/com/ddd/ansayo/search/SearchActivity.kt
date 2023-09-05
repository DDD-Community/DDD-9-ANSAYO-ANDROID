package com.ddd.ansayo.search

import android.os.Bundle
import com.ddd.ansayo.base.BaseActivity
import com.ddd.ansayo.databinding.ActivitySearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchActivity :
    BaseActivity<ActivitySearchBinding>(ActivitySearchBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
    }
    private fun initView() {
        binding.run {
            vSearch.showKeyboard()
            vSearch.searchText.observe(this@SearchActivity) {
                startActivity(SearchListActivity.getIntent(this@SearchActivity, it))

            }
        }
    }
}