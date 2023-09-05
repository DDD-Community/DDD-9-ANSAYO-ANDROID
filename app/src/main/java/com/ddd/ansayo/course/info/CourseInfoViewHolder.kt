package com.ddd.ansayo.course.info

import androidx.recyclerview.widget.RecyclerView
import com.ddd.ansayo.core_design.R
import com.ddd.ansayo.core_model.place.Place
import com.ddd.ansayo.databinding.ItemCourseInfoPlaceBinding

class CourseInfoViewHolder (
    private val binding: ItemCourseInfoPlaceBinding,
    private val placeClickListener: (String) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    init {
        binding.root.setOnClickListener {
            binding.place?.let { placeClickListener(it.placeId) }
        }
    }

    fun onBind(item: Place, position: Int) {
        binding.place = item
        val imageResource = when (position) {
            0 -> R.drawable.img_review_no1
            1 -> R.drawable.img_review_no2
            2 -> R.drawable.img_review_no3
            3 -> R.drawable.img_review_no4
            4 -> R.drawable.img_review_no5
            5 -> R.drawable.img_review_no6
            6 -> R.drawable.img_review_no7
            else -> R.drawable.img_review_no8
        }
        binding.ivPlaceNums.setImageResource(imageResource)
    }
}