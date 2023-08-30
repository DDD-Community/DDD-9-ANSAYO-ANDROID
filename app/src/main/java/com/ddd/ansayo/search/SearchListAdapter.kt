package com.ddd.ansayo.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class SearchListAdapter(
    fragmentActivity: FragmentActivity,
    searchKeyword: String
) : FragmentStateAdapter(fragmentActivity) {


    private val fragments = listOf(
        SearchCourseFragment(),
        SearchPlaceFragment().apply {
            arguments = Bundle().apply {
                putString("searchKeyword", searchKeyword)
            }
        }
    )
    override fun getItemCount(): Int  = fragments.size


    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}