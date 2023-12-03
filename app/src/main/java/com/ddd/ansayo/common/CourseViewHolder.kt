package com.ddd.ansayo.common

import androidx.recyclerview.widget.RecyclerView
import com.ddd.ansayo.base.BaseBindingAdapter.setImageUrl
import com.ddd.ansayo.core_model.course.Course
import com.ddd.ansayo.databinding.ItemCourseBinding

class CourseViewHolder(
    private val binding: ItemCourseBinding,
    private val courseClickListener: ((Course) -> Unit)
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(item: Course) {
        with(binding) {
            thumbnail.setImageUrl(item.titleImage)
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
