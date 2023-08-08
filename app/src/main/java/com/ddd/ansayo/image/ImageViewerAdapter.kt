package com.ddd.ansayo.image

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ddd.ansayo.databinding.ItemImageViewerBinding

class ImageViewerAdapter(
    private val images: List<String>
) : RecyclerView.Adapter<ImageViewerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemImageViewerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(images[position])
    }

    override fun getItemCount(): Int = images.size

    class ViewHolder(private val binding: ItemImageViewerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(url: String) {
            binding.imageUrl = url
        }
    }
}
