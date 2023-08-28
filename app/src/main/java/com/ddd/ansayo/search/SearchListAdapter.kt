package com.ddd.ansayo.search

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class SearchListAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    private val fragments = listOf(
        SearchCourseFragment(),
        SearchPlaceFragment()
    )
    override fun getItemCount(): Int  = fragments.size


    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}