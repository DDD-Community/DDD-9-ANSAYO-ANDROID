package com.ddd.ansayo.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ListAdapter
import com.ddd.ansayo.core_design.R
import com.ddd.ansayo.core_model.badge.Badge
import com.ddd.ansayo.databinding.ItemSearchableBadgeBinding
import com.ddd.ansayo.util.ItemCallback

class SearchableBadgeAdapter(
    private val badgeClickListener: (Badge) -> Unit
) : ListAdapter<Badge, SearchableBadgeAdapter.ViewHolder>(
    ItemCallback(
        itemTheSame = { oldItem, newItem -> oldItem.id == newItem.id },
        contentsTheSame = { oldItem, newItem -> oldItem == newItem }
    )
) {
    val selectedBadge =  MutableLiveData<Badge>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemSearchableBadgeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding,badgeClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(getItem(position), isSelectBadge(getItem(position)))
    }

    fun setSelectedBadge(badge: Badge) {
        selectedBadge.value = badge

        notifyItemRangeChanged(0, itemCount)
    }
    private fun isSelectBadge(badge: Badge): Boolean = badge.id == selectedBadge.value?.id

    class ViewHolder(
        private val binding: ItemSearchableBadgeBinding,
        private val badgeClickListener: (Badge) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                binding.badge?.let { badgeClickListener(it) }
            }
        }
        fun onBind(item: Badge, isSelected: Boolean) {
           binding.badge = item
           with(binding) {
               if (isSelected) {
                   tvBadge.setTextColor(itemView.context.getColor(R.color.orange_point))
                   ivBadge.foreground= ContextCompat.getDrawable(itemView.context, R.drawable.bg_select_badge)
               }else {
                   tvBadge.setTextColor(itemView.context.getColor(R.color.N40))
               }
           }
        }
    }

}