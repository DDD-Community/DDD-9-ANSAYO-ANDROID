package com.ddd.ansayo.place

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ddd.ansayo.databinding.ItemPlaceDetailInfoBinding
import com.ddd.ansayo.domain.model.place.PlaceDetail

class PlaceDetailInfoAdapter(
    private val favoriteClickListener: (Boolean) -> Unit
) : RecyclerView.Adapter<PlaceDetailInfoAdapter.ViewHolder>() {

    private var header: PlaceDetail? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemPlaceDetailInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, favoriteClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        header?.let { holder.onBind(it) }
    }

    override fun getItemCount(): Int {
        return if (header == null) 0 else 1
    }

    fun onChanged(item: PlaceDetail) {
        header = item
        notifyItemChanged(0)
    }

    class ViewHolder(
        private val binding: ItemPlaceDetailInfoBinding,
        private val favoriteClickListener: (Boolean) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.ivFavorite.setOnClickListener {
                binding.placeDetail?.let { favoriteClickListener(it.isFavorite) }
            }
        }

        fun onBind(item: PlaceDetail) {
            binding.placeDetail = item
        }
    }
}
