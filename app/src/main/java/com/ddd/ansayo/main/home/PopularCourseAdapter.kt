package com.ddd.ansayo.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ddd.ansayo.core_model.course.Course
import com.ddd.ansayo.databinding.ItemPopularCourseBinding
import com.ddd.ansayo.util.ItemCallback

class PopularCourseAdapter (
    private val courseClickListener: (String) -> Unit
) : ListAdapter<Course, PopularCourseAdapter.ViewHolder>(
    ItemCallback(
        itemTheSame = { oldItem, newItem -> oldItem.id == newItem.id },
        contentsTheSame = { oldItem, newItem -> oldItem == newItem }
    )
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemPopularCourseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, courseClickListener)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
    class ViewHolder(
        private val binding: ItemPopularCourseBinding,
        private val courseClickListener: (String) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                binding.couse?.let { courseClickListener(it.id) }
            }
        }
        fun onBind(item: Course) {
            binding.couse = item
        }
    }
}
