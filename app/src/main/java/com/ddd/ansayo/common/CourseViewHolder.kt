package com.ddd.ansayo.common

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.ddd.ansayo.core_model.course.Course
import com.ddd.ansayo.databinding.ItemCourseBinding
import com.ddd.ansayo.util.load
import com.orhanobut.logger.Logger

class CourseViewHolder(
    private val binding: ItemCourseBinding,
    private val courseClickListener: ((Course) -> Unit)
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(item: Course) {
        with(binding) {
            thumbnail.load(item.titleImage)
            tvCourseTitle.text = item.name
            tvCourseCategory.text = item.category
            tvCourseDescription.text = item.review
            tvLikeCount.text = item.favorites.toString()


            root.setOnClickListener {
                courseClickListener.invoke(item)
            }
        }

    }
}
