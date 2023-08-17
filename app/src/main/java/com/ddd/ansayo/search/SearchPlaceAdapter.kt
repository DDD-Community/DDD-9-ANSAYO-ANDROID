package com.ddd.ansayo.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ddd.ansayo.core_model.place.Place
import com.ddd.ansayo.databinding.ItemSearchPlaceBinding
import com.ddd.ansayo.util.ItemCallback

class SearchPlaceAdapter (
    private val placeClickListener: (String) -> Unit
) : ListAdapter<Place, SearchPlaceAdapter.ViewHolder>(
    ItemCallback(
        itemTheSame = { oldItem, newItem -> oldItem.placeId == newItem.placeId },
        contentsTheSame = { oldItem, newItem -> oldItem == newItem }
    )
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSearchPlaceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, placeClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
    class ViewHolder(
        private val binding: ItemSearchPlaceBinding,
        private val placeClickListener: (String) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                binding.place?.let { placeClickListener(it.placeId) }
            }

        }
        fun onBind(item: Place) {
            binding.place = item
        }
    }
}