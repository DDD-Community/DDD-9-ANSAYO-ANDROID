package com.ddd.ansayo.place

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ddd.ansayo.core_model.place.Place
import com.ddd.ansayo.databinding.ItemSearchAddPlaceBinding
import com.ddd.ansayo.util.ItemCallback

class SearchAddPlaceAdapter(
    private val clickAddPlaceListener: ((String) -> Unit),
    private val clickShowDetailListener: ((String) -> Unit)
) : ListAdapter<Place, SearchAddPlaceAdapter.ViewHolder>(
    ItemCallback(
        itemTheSame = { oldItem, newItem -> oldItem.placeId == newItem.placeId },
        contentsTheSame = { oldItem, newItem -> oldItem == newItem }
    )
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemSearchAddPlaceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, clickAddPlaceListener, clickShowDetailListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    class ViewHolder(
        private val binding: ItemSearchAddPlaceBinding,
        private val clickAddPlaceListener: ((String) -> Unit),
        private val clickShowDetailListener: ((String) -> Unit)
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                binding.place?.let { place ->
                    clickAddPlaceListener.invoke(place.placeId)
                }
            }

            binding.tvShowDetail.setOnClickListener {
                binding.place?.let { place ->
                    clickShowDetailListener.invoke(place.placeId)
                }
            }
        }

        fun onBind(item: Place) {
            binding.place = item
        }
    }
}
