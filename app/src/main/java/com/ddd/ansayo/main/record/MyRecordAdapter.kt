package com.ddd.ansayo.main.record

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.ddd.ansayo.common.CourseViewHolder
import com.ddd.ansayo.core_model.course.Course
import com.ddd.ansayo.databinding.ItemCourseBinding
import com.ddd.ansayo.util.ItemCallback

class MyRecordAdapter(
    private val courseClickListener: ((Course) -> Unit)
) : ListAdapter<Course, CourseViewHolder>(
    ItemCallback(
        itemTheSame = { oldItem, newItem -> oldItem == newItem },
        contentsTheSame = { oldItem, newItem -> oldItem == newItem }
    )
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val binding = ItemCourseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CourseViewHolder(binding, courseClickListener)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
}
