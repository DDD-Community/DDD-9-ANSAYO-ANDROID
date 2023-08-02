package com.ddd.ansayo.course

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ddd.ansayo.util.ItemCallback
import com.ddd.ansayo.databinding.ItemAddPlaceBinding
import com.ddd.ansayo.databinding.ItemCourseWritePlaceBinding
import com.ddd.ansayo.databinding.ItemFirstAddPlaceBinding
import com.ddd.ansayo.domain.model.course.CourseWriteState

class CourseWritePlaceAdapter(
    private val placeReviewChangedListener: ((Int, String) -> Unit),
    private val placeDeleteClickListener: ((Int) -> Unit),
    private val placeAddClickListener: (() -> Unit),
    private val placeImageAddClickListener: ((Int) -> Unit),
    private val placeImageDeleteClickListener: ((Int, Int) -> Unit)
) : ListAdapter<CourseWriteState.Place, RecyclerView.ViewHolder>(
    ItemCallback<CourseWriteState.Place>(
        itemTheSame = { oldItem, newItem -> oldItem == newItem },
        contentsTheSame = { oldItem, newItem -> oldItem == newItem }
    )
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            FIRST_ADD_PLACE -> {
                val binding = ItemFirstAddPlaceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                FirstAddViewHolder(
                    binding,
                    placeAddClickListener
                )
            }

            ADD_PLACE -> {
                val binding =
                    ItemAddPlaceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                AddViewHolder(binding, placeAddClickListener)
            }

            else -> {
                val binding = ItemCourseWritePlaceBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                PlaceViewHolder(
                    binding,
                    placeReviewChangedListener,
                    placeDeleteClickListener,
                    placeImageAddClickListener,
                    placeImageDeleteClickListener
                )
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is PlaceViewHolder -> holder.onBind(getItem(position))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            currentList.isEmpty() -> {
                FIRST_ADD_PLACE
            }

            position == currentList.lastIndex -> {
                ADD_PLACE
            }

            else -> {
                PLACE
            }
        }
    }

    override fun getItemCount(): Int {
        return currentList.size + 1
    }

    class PlaceViewHolder(
        private val binding: ItemCourseWritePlaceBinding,
        private val placeReviewChangedListener: ((Int, String) -> Unit),
        private val placeDeleteClickListener: ((Int) -> Unit),
        private val placeImageAddClickListener: ((Int) -> Unit),
        private val placeImageDeleteClickListener: ((Int, Int) -> Unit)
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.etPlaceReview.doAfterTextChanged {
                binding.place?.let { place ->
                    placeReviewChangedListener.invoke(place.order, it.toString())
                }
            }

            binding.ivDelete.setOnClickListener {
                binding.place?.let { place ->
                    placeDeleteClickListener.invoke(place.order)
                }
            }

            binding.rvPlaceImage.apply {
                adapter = CoursePlaceImageAdapter(
                    placeImageAddClickListener = {
                        binding.place?.let { place ->
                            placeImageAddClickListener.invoke(place.order)
                        }
                    },
                    placeImageDeleteClickListener = { imageIndex ->
                        binding.place?.let { place ->
                            placeImageDeleteClickListener.invoke(place.order, imageIndex)
                        }
                    }
                )
                layoutManager = LinearLayoutManager(context)
                itemAnimator = null
                setHasFixedSize(true)
            }
        }

        fun onBind(item: CourseWriteState.Place) {
            binding.place = item
        }
    }

    class FirstAddViewHolder(
        binding: ItemFirstAddPlaceBinding,
        private val placeAddClickListener: (() -> Unit)
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.btnFirstAdd.setOnClickListener {
                placeAddClickListener.invoke()
            }
        }
    }

    class AddViewHolder(
        binding: ItemAddPlaceBinding,
        private val placeAddClickListener: (() -> Unit)
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.btnAdd.setOnClickListener {
                placeAddClickListener.invoke()
            }
        }
    }

    companion object {
        private const val FIRST_ADD_PLACE = 0
        private const val ADD_PLACE = 1
        private const val PLACE = 2
    }
}
