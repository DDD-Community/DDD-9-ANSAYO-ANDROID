package com.ddd.ansayo.main.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ddd.ansayo.core_model.place.Place
import com.ddd.ansayo.databinding.ItemFavoritePlaceBinding
import com.ddd.ansayo.util.ItemCallback

class FavoritePlaceAdapter(
    private val placeClickListener: (String) -> Unit,
    private val favoriteClickListener: (String, Boolean) -> Unit
) : ListAdapter<Place, FavoritePlaceAdapter.ViewHolder>(
    ItemCallback(
        itemTheSame = { oldItem, newItem -> oldItem.placeId == newItem.placeId },
        contentsTheSame = { oldItem, newItem -> oldItem == newItem }
    )
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemFavoritePlaceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, placeClickListener, favoriteClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    class ViewHolder(
        private val binding: ItemFavoritePlaceBinding,
        private val placeClickListener: (String) -> Unit,
        private val favoriteClickListener: (String, Boolean) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                binding.place?.let {
                    placeClickListener(it.placeId)
                }
            }

            binding.ivFavorite.setOnClickListener {
                binding.place?.let {
                    favoriteClickListener(it.placeId, binding.ivFavorite.isSelected)
                }
            }
        }

        fun onBind(item: Place) {
            binding.place = item
        }
    }
}
