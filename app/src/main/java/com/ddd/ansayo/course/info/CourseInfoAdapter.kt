package com.ddd.ansayo.course.info

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ddd.ansayo.core_model.course.CourseInfo
import com.ddd.ansayo.databinding.ItemCourseInfoDetailBinding

class CourseInfoAdapter(
    private val favoriteClickListener: (Boolean) -> Unit
) : RecyclerView.Adapter<CourseInfoAdapter.ViewHolder>() {

    private var header: CourseInfo? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemCourseInfoDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, favoriteClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        header?.let { holder.onBind(it) }
    }

    override fun getItemCount(): Int {
        return if (header == null) 0 else 1
    }

    fun onChanged(item: CourseInfo) {
        header = item
        notifyItemChanged(0)
    }

    class ViewHolder(
        private val binding: ItemCourseInfoDetailBinding,
        private val favoriteClickListener: (Boolean) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.ivCourseLike.setOnClickListener {
                binding.course?.let { favoriteClickListener(it.course.isFavorite) }
            }
        }

        fun onBind(item: CourseInfo) {
            binding.course = item
        }
    }
}