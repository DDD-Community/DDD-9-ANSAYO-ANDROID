package com.ddd.ansayo.base

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object BaseBindingAdapter {

    @JvmStatic
    @BindingAdapter("isVisible")
    fun View.isVisible(isVisible: Boolean) {
        this.isVisible = isVisible
    }

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun ImageView.setImageUrl(url: String?) {
        Glide.with(this)
            .load(url)
            .into(this)
    }

    @JvmStatic
    @BindingAdapter("isSelected")
    fun View.setIsSelected(isSelected: Boolean) {
        this.isSelected = isSelected
    }
}
