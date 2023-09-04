package com.ddd.ansayo.search

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.ddd.ansayo.core_model.place.Photo
import com.ddd.ansayo.extenstion.dpToPx

object SearchBindingAdapter {
    @JvmStatic
    @BindingAdapter("searchFavorites")
    fun TextView.setSearchFavorites(favorites: Int?) {
        favorites?.let {
            text = "$it"
        }
    }
    @JvmStatic
    @BindingAdapter("placeType")
    fun TextView.setPlaceType(type: List<String>?) {
        type?.let {
            text = "${it.first()}"
        }
    }

    @JvmStatic
    @BindingAdapter("searchThumbnail")
    fun ImageView.setSearchThumbnail(url: List<Photo>?) {
        url?.let {
            Glide.with(context)
                .load(it?.firstOrNull()?.photoPath)
                .transform(CenterCrop(), RoundedCorners(12.dpToPx().toInt()))
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(this)
        }
    }


}