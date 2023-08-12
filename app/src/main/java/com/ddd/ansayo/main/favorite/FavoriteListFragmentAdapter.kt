package com.ddd.ansayo.main.favorite

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class FavoriteListFragmentAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val fragments = listOf(
        FavoriteCourseFragment(),
        FavoritePlaceFragment()
    )

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}
