package com.ddd.ansayo.common

import androidx.recyclerview.widget.RecyclerView
import com.ddd.ansayo.databinding.ItemCourseBinding
import com.ddd.ansayo.domain.model.course.Course

class CourseViewHolder(
    private val binding: ItemCourseBinding,
    private val courseClickListener: ((Course) -> Unit)
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.root.setOnClickListener {
            binding.course?.let {
                courseClickListener.invoke(it)
            }
        }
    }

    fun onBind(item: Course) {
        binding.course = item
    }
}