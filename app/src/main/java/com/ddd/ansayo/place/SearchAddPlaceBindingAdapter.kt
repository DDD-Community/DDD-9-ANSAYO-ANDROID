package com.ddd.ansayo.place

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.ddd.ansayo.R
import com.ddd.ansayo.core_model.place.Photo

object SearchAddPlaceBindingAdapter {
    
    @JvmStatic
    @BindingAdapter("placeAddress", "placeTypes")
    fun TextView.setPlaceCategory(address: String, types: List<String>?) {
        val parsedAddress = address.split(" ").getOrElse(2) {
            "기타"
        }
        val type = types?.firstOrNull() ?: "기타"
        text = context.getString(R.string.category, parsedAddress, type)
    }

    @JvmStatic
    @BindingAdapter("placeImage")
    fun ImageView.setPlaceImage(photos: List<Photo>?) {
        Glide.with(this)
            .load(photos?.firstOrNull()?.photoPath)
            .into(this)
    }
}
