package com.ddd.ansayo.search.keyword

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ddd.ansayo.core_model.search.RecentKeyword

object SearchkeywordBindingAdapter {

    @JvmStatic
    @BindingAdapter("recentSearchKeywords")
    fun RecyclerView.setRecentSearchKeyword(list: List<RecentKeyword>?) {
        list?.let { (adapter as? SearchkeywordAdapter)?.updateItems(it) }
    }
}