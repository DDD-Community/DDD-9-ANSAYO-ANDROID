package com.ddd.ansayo.course

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.ddd.ansayo.R

object CourseBindingAdapter {

    @JvmStatic
    @BindingAdapter("courseDate")
    fun TextView.setCourseDate(date: String) {
        if (date.isEmpty()) {
            text = context.getString(R.string.course_date_hint)
            setTextColor(context.getColor(R.color.black))
        } else {
            text = date
            setTextColor(context.getColor(R.color.black))
        }
    }
}
