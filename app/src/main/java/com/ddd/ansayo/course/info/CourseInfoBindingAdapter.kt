package com.ddd.ansayo.course.info

import android.widget.ImageView
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
}