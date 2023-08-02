package com.ddd.ansayo.course

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ddd.ansayo.databinding.ItemAddPlaceImageBinding
import com.ddd.ansayo.databinding.ItemCoursePlaceImageBinding
import com.ddd.ansayo.domain.model.course.CoursePlaceImage
import com.ddd.ansayo.util.ItemCallback

class CoursePlaceImageAdapter(
    private val placeImageAddClickListener: (() -> Unit),
    private val placeImageDeleteClickListener: ((Int) -> Unit)
) : ListAdapter<CoursePlaceImage, RecyclerView.ViewHolder>(
    ItemCallback(
        itemTheSame = { oldItem, newItem -> oldItem.origin == newItem.origin },
        contentsTheSame = { oldItem, newItem -> oldItem.origin == newItem.origin }
    )
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ADD -> {
                val binding = ItemAddPlaceImageBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                AddViewHolder(binding, placeImageAddClickListener)
            }

            else -> {
                val binding = ItemCoursePlaceImageBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                ImageViewHolder(binding, placeImageDeleteClickListener)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ImageViewHolder -> holder.onBind(getItem(position))
            is AddViewHolder -> holder.onBind(currentList.size)
        }
    }

    override fun getItemCount(): Int {
        return currentList.size + 1
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> ADD
            else -> IMAGE
        }
    }

    class ImageViewHolder(
        private val binding: ItemCoursePlaceImageBinding,
        private val placeImageDeleteClickListener: ((Int) -> Unit)
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.ivDeleteImage.setOnClickListener {
                placeImageDeleteClickListener.invoke(absoluteAdapterPosition)
            }
        }

        fun onBind(item: CoursePlaceImage) {
            binding.image = item
        }
    }

    class AddViewHolder(
        private val binding: ItemAddPlaceImageBinding,
        private val placeImageAddClickListener: (() -> Unit),
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.cvAdd.setOnClickListener {
                placeImageAddClickListener.invoke()
            }
        }

        fun onBind(currentCount: Int) {
            binding.currentCount = currentCount
        }
    }

    companion object {
        private const val ADD = 0
        private const val IMAGE = 1
    }
}
