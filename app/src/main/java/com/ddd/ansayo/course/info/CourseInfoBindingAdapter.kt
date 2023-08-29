package com.ddd.ansayo.course.info

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.ddd.ansayo.extenstion.dpToPx

object CourseInfoBindingAdapter {
    @JvmStatic
    @BindingAdapter("placeThumbnail")
    fun ImageView.setPlaceThumbnail(url: String?) {
        url?.let {
            Glide.with(context)
                .load(url)
                .transform(CenterCrop(), RoundedCorners(12.dpToPx().toInt()))
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(this)
        }
    }
    @JvmStatic
    @BindingAdapter("placeName")
    fun TextView.setPlaceName(name: String?) {
        text = name?.let {
            it
        }
    }
    @JvmStatic
    @BindingAdapter("placeReview")
    fun TextView.setPlaceReview(review: String?) {
        text = review?.let {
            it
        }
    }

}