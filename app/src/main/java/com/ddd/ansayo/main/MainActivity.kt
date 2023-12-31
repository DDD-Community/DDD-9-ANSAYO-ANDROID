package com.ddd.ansayo.main

import android.content.Intent
import android.os.Bundle
import androidx.core.os.bundleOf
import com.ddd.ansayo.R
import com.ddd.ansayo.base.BaseActivity
import com.ddd.ansayo.data.AuthLocalDataSource
import com.ddd.ansayo.databinding.ActivityMainBinding
import com.ddd.ansayo.presentation.viewmodel.Constant
import com.ddd.ansayo.search.SearchActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    @Inject
    lateinit var authLocalDataSource: AuthLocalDataSource
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity( Intent(this, SearchActivity::class.java))
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
                R.id.menu_home -> {
                    binding.vpFragment.setCurrentItem(MainFragmentAdapter.HOME, false)
                }
                R.id.menu_my_record -> {
                    binding.vpFragment.setCurrentItem(MainFragmentAdapter.MY_RECORD, false)
                    supportFragmentManager.setFragmentResult(Constant.SELECT_MY_RECORD_TAB, bundleOf())
                }
                R.id.menu_favorite -> {
                    binding.vpFragment.setCurrentItem(MainFragmentAdapter.FAVORITE, false)
                    supportFragmentManager.setFragmentResult(Constant.SELECT_FAVORITE_TAB, bundleOf())
                }
                R.id.menu_my_page -> {
                    binding.vpFragment.setCurrentItem(MainFragmentAdapter.MY_PAGE, false)
                }
            }
            true
        }
    }
}
