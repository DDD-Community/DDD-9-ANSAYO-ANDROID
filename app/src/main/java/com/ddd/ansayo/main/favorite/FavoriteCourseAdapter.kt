package com.ddd.ansayo.main.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ddd.ansayo.core_model.course.Course
import com.ddd.ansayo.databinding.ItemFavoriteCourseBinding
import com.ddd.ansayo.util.ItemCallback

class FavoriteCourseAdapter(
    private val courseClickListener: (String) -> Unit,
    private val favoriteClickListener: (String, Boolean) -> Unit
) : ListAdapter<Course, FavoriteCourseAdapter.ViewHolder>(
    ItemCallback(
        itemTheSame = { oldItem, newItem -> oldItem.id == newItem.id },
        contentsTheSame = { oldItem, newItem -> oldItem == newItem }
    )
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemFavoriteCourseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, courseClickListener, favoriteClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    class ViewHolder(
        private val binding: ItemFavoriteCourseBinding,
        private val courseClickListener: (String) -> Unit,
        private val favoriteClickListener: (String, Boolean) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                binding.course?.let { courseClickListener(it.id) }
            }

            binding.ivFavorite.setOnClickListener {
                binding.course?.let {
                    favoriteClickListener(it.id, binding.ivFavorite.isSelected)
                }
            }
        }

        fun onBind(item: Course) {
            binding.course = item
        }
    }
}
