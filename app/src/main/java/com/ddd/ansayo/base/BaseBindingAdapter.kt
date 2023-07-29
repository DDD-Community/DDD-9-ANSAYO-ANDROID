package com.ddd.ansayo.base

import android.view.View
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter

object BaseBindingAdapter {

    @JvmStatic
    @BindingAdapter("isVisible")
    fun View.isVisible(isVisible: Boolean) {
        this.isVisible = isVisible
    }
}
