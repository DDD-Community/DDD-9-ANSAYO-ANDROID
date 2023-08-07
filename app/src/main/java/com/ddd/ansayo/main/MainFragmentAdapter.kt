package com.ddd.ansayo.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ddd.ansayo.main.favorite.FavoriteListFragment
import com.ddd.ansayo.main.home.HomeFragment
import com.ddd.ansayo.main.mypage.MyPageFragment
import com.ddd.ansayo.main.record.MyRecordFragment

class MainFragmentAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    private val fragments = listOf(
        HomeFragment(),
        MyRecordFragment(),
        FavoriteListFragment(),
        MyPageFragment()
    )

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

    companion object {
        const val HOME = 0
        const val MY_RECORD = 1
        const val FAVORITE = 2
        const val MY_PAGE = 3
    }
}
