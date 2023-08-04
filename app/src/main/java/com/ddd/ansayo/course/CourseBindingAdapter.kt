package com.ddd.ansayo.course

import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.ddd.ansayo.R
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
        // TODO
    }

    @JvmStatic
    @BindingAdapter("placeImageCount")
    fun TextView.setPlaceImageCount(currentCount: Int) {
        text = context.getString(R.string.course_place_image_count, currentCount, 4)

    }
}
