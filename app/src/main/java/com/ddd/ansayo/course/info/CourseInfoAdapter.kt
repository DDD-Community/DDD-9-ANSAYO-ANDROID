package com.ddd.ansayo.course.info

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ddd.ansayo.R
import com.ddd.ansayo.core_model.place.Place
import com.ddd.ansayo.databinding.ItemCourseInfoPlaceBinding
import com.ddd.ansayo.util.ItemCallback

class CourseInfoAdapter(
    private val placeClickListener: (String) -> Unit
) : ListAdapter<Place, CourseInfoAdapter.ViewHolder>(
    ItemCallback(
        itemTheSame = { oldItem, newItem -> oldItem.placeId == newItem.placeId },
        contentsTheSame = { oldItem, newItem -> oldItem == newItem }
    )
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemCourseInfoPlaceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, placeClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(getItem(position),position)
    }

    class ViewHolder(
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
                0 -> com.ddd.ansayo.core_design.R.drawable.img_review_no1
                1 -> com.ddd.ansayo.core_design.R.drawable.img_review_no2
                2 -> com.ddd.ansayo.core_design.R.drawable.img_review_no3
                3 -> com.ddd.ansayo.core_design.R.drawable.img_review_no4
                4 -> com.ddd.ansayo.core_design.R.drawable.img_review_no5
                5 -> com.ddd.ansayo.core_design.R.drawable.img_review_no6
                6 -> com.ddd.ansayo.core_design.R.drawable.img_review_no7
                else -> com.ddd.ansayo.core_design.R.drawable.img_review_no8
            }
            binding.ivPlaceNums.setImageResource(imageResource)
        }
    }
}