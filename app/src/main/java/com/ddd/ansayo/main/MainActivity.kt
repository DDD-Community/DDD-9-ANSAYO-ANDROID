package com.ddd.ansayo.main

import android.os.Bundle
import com.ddd.ansayo.R
import com.ddd.ansayo.base.BaseActivity
import com.ddd.ansayo.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
    }

    private fun initView() {
        binding.vpFragment.apply {
            adapter = MainFragmentAdapter(this@MainActivity)
            isUserInputEnabled = false
            offscreenPageLimit = 4
        }

        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> binding.vpFragment.currentItem = MainFragmentAdapter.HOME
                R.id.menu_my_record -> binding.vpFragment.currentItem = MainFragmentAdapter.MY_RECORD
                R.id.menu_favorite -> binding.vpFragment.currentItem = MainFragmentAdapter.FAVORITE
                R.id.menu_my_page -> binding.vpFragment.currentItem = MainFragmentAdapter.MY_PAGE
            }
            true
        }
    }
}
