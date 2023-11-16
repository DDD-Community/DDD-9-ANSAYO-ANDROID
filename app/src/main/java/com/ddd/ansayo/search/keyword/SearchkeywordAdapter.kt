package com.ddd.ansayo.search.keyword

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ddd.ansayo.core_model.search.RecentKeyword
import com.ddd.ansayo.databinding.ItemRecentKeywordBinding

class SearchkeywordAdapter(
    private val keywordClickListener: (String) -> Unit,
    private val deleteClickListener: (RecentKeyword) -> Unit
) : RecyclerView.Adapter<SearchkeywordAdapter.ViewHolder>() {

    private val items = mutableListOf<RecentKeyword>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemRecentKeywordBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, keywordClickListener, deleteClickListener)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }
    class ViewHolder(
        private val binding: ItemRecentKeywordBinding,
        private val keywordClickListener: (String) -> Unit,
        private val deleteClickListener: (RecentKeyword) -> Unit
    ): RecyclerView.ViewHolder(binding.root) {

        init {
            binding.tvKeyword.setOnClickListener {
                binding.keyword?.let { keywordClickListener(it.keyword) }
            }
            binding.ivRemove.setOnClickListener {
                binding.keyword?.let { deleteClickListener(it) }
            }
        }
        fun bind(item: RecentKeyword) {
            binding.keyword = item
        }
    }
    fun updateItems(newItems: List<RecentKeyword>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }
}