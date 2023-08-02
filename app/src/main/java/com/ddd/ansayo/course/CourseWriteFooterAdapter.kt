package com.ddd.ansayo.course

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ddd.ansayo.databinding.ItemCourseWriteFooterBinding
import com.ddd.ansayo.domain.model.course.CourseWriteState

class CourseWriteFooterAdapter(
    private val visibilityChangedListener: ((Boolean) -> Unit),
    private val completeClickListener: (() -> Unit)
) : RecyclerView.Adapter<CourseWriteFooterAdapter.ViewHolder>() {

    private var footer = CourseWriteState.Footer.EMPTY

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCourseWriteFooterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, visibilityChangedListener, completeClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(footer)
    }

    override fun getItemCount(): Int = 1

    fun onChanged(item: CourseWriteState.Footer) {
        this.footer = item
        notifyItemChanged(0)
    }

    class ViewHolder(
        private val binding: ItemCourseWriteFooterBinding,
        private val visibilityChangedListener: ((Boolean) -> Unit),
        private val completeClickListener: (() -> Unit)
    ): RecyclerView.ViewHolder(binding.root) {

        init {
            binding.switchVisibility.setOnCheckedChangeListener { _, isChecked ->
                visibilityChangedListener.invoke(isChecked)
            }

            binding.btnComplete.setOnClickListener {
                completeClickListener.invoke()
            }
        }

        fun onBind(item: CourseWriteState.Footer) {
            binding.footer = item
        }
    }
}
