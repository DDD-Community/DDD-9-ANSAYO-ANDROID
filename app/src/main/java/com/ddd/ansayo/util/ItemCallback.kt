package com.ddd.ansayo.util

import androidx.recyclerview.widget.DiffUtil

class ItemCallback<T : Any> constructor(
    private val itemTheSame: (oldItem:T, newItem: T) -> Boolean,
    private val contentsTheSame: (oldItem:T, newItem: T) -> Boolean
) : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return itemTheSame(oldItem, newItem)
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return contentsTheSame(oldItem, newItem)
    }
}
