package com.ddd.ansayo.course

import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.ddd.ansayo.R
import com.ddd.ansayo.extenstion.setHtmlText
import com.ddd.ansayo.core_design.R as coreDesignR

object CourseBindingAdapter {

    @JvmStatic
    @BindingAdapter("courseDate")
    fun TextView.setCourseDate(date: String) {
        if (date.isEmpty()) {
            text = context.getString(R.string.course_date_hint)
            setTextColor(context.getColor(coreDesignR.color.N0))
        } else {
            text = date
            setTextColor(context.getColor(coreDesignR.color.N0))
        }
    }

    @JvmStatic
    @BindingAdapter("placeOrder")
    fun ViewGroup.setPlaceOrder(order: Int) {
        val background = when (order.rem(4)) {
            0 -> ContextCompat.getDrawable(context, coreDesignR.drawable.bg_circle_pink_sub)
            1 -> ContextCompat.getDrawable(context, coreDesignR.drawable.bg_circle_orange_sub)
            2 -> ContextCompat.getDrawable(context, coreDesignR.drawable.bg_circle_green_sub)
            else -> ContextCompat.getDrawable(context, coreDesignR.drawable.bg_circle_blue_sub)
        }
        setBackground(background)
    }

    @JvmStatic
    @BindingAdapter("placeImageCount")
    fun TextView.setPlaceImageCount(currentCount: Int) {
        text = context.getString(R.string.course_place_image_count, currentCount, 4)
    }
}
