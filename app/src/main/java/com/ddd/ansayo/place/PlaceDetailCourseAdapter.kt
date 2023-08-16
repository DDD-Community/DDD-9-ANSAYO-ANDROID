package com.ddd.ansayo.place

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ddd.ansayo.common.CourseViewHolder
import com.ddd.ansayo.core_model.course.Course
import com.ddd.ansayo.databinding.ItemCourseBinding
import com.ddd.ansayo.databinding.ItemPlaceDetailCourseHeaderBinding
import com.ddd.ansayo.util.ItemCallback

class PlaceDetailCourseAdapter(
    private val courseClickListener: (Course) -> Unit
): ListAdapter<Course, RecyclerView.ViewHolder>(
    ItemCallback(
        itemTheSame = { oldItem, newItem -> oldItem.id == newItem.id },
        contentsTheSame = { oldItem, newItem -> oldItem == newItem }
    )
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == HEADER) {
            val binding = ItemPlaceDetailCourseHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            HeaderViewHolder(binding, courseClickListener)
        } else {
            val binding = ItemCourseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            CourseViewHolder(binding, courseClickListener)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HeaderViewHolder -> holder.onBind(getItem(position))
            is CourseViewHolder -> holder.onBind(getItem(position))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) HEADER else BODY
    }

    class HeaderViewHolder(
        private val binding: ItemPlaceDetailCourseHeaderBinding,
        private val courseClickListener: (Course) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.layoutCourse.setOnClickListener {
                binding.course?.let(courseClickListener)
            }
        }

        fun onBind(item: Course) {
            binding.course = item
        }
    }

    companion object {
        private const val HEADER = 0
        private const val BODY = 1
    }
}
