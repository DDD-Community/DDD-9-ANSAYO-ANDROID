package com.ddd.ansayo.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ListAdapter
import com.ddd.ansayo.core_model.place.Place
import com.ddd.ansayo.databinding.ItemSearchableBadgeBinding
import com.ddd.ansayo.util.ItemCallback

class SearchableBadgeAdapter(
    private val badgeClickListener: (String) -> Unit
) : ListAdapter<Place, SearchableBadgeAdapter.ViewHolder>(
    ItemCallback(
        itemTheSame = { oldItem, newItem -> oldItem.placeId == newItem.placeId },
        contentsTheSame = { oldItem, newItem -> oldItem == newItem }
    )
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemSearchableBadgeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, badgeClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    class ViewHolder(
        private val binding: ItemSearchableBadgeBinding,
        private val badgeClickListener: (String) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                binding.place?.let { badgeClickListener(it.placeId) }
            }
        }

        fun onBind(item: Place) {
            binding.place = item
        }
    }

}