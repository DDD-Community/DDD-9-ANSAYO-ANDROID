package com.ddd.ansayo.search

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

object SearchBindingAdapter {
    @JvmStatic
    @BindingAdapter("searchFavorites")
    fun TextView.setSearchFavorites(favorites: Int?) {
        favorites?.let {
            text = "$it"
        }
    }
    @JvmStatic
    @BindingAdapter("searchThumbnail")
    fun ImageView.setSearchThumbnail(url: String?) {
        url?.let {
            Glide.with(context)
                .load(url)
//                .transform(CenterCrop(), RoundedCorners(12.dpToPx().toInt()))
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(this)
        }
    }


}