package com.ddd.ansayo.place

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.ddd.ansayo.R

object SearchAddPlaceBindingAdapter {
    
    @JvmStatic
    @BindingAdapter("placeAddress", "placeTypes")
    fun TextView.setPlaceCategory(address: String, types: List<String>?) {
        val type = types?.firstOrNull() ?: "기타"
        text = context.getString(R.string.category, address, type)
    }

    @JvmStatic
    @BindingAdapter("placeRating", "placeReviewCount")
    fun TextView.setPlaceRating(rating: Int, totalCount: Int) {
        text = context.getString(R.string.rating, rating, totalCount)
    }
}
