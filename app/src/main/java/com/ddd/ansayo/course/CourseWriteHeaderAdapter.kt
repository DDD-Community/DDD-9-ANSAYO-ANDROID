package com.ddd.ansayo.course

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.ddd.ansayo.R
import com.ddd.ansayo.databinding.ItemCourseWriteHeaderBinding
import com.ddd.ansayo.domain.model.course.CourseWriteState
import com.ddd.ansayo.extenstion.setHtmlText

class CourseWriteHeaderAdapter(
    private val courseTitleChangedListener: ((String) -> Unit),
    private val courseDescriptionChangedListener: ((String) -> Unit),
    private val dateClickListener: (() -> Unit)
) : RecyclerView.Adapter<CourseWriteHeaderAdapter.ViewHolder>() {

    private var header = CourseWriteState.Header.EMPTY

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCourseWriteHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, courseTitleChangedListener, courseDescriptionChangedListener, dateClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(header)
    }

    override fun getItemCount(): Int = 1

    fun onChanged(item: CourseWriteState.Header) {
        header = item
        notifyItemChanged(0)
    }

    class ViewHolder(
        private val binding: ItemCourseWriteHeaderBinding,
        private val courseTitleChangedListener: ((String) -> Unit),
        private val courseDescriptionChangedListener: ((String) -> Unit),
        private val dateClickListener: (() -> Unit)
    ): RecyclerView.ViewHolder(binding.root) {

        init {
            binding.etCourseTitle.doAfterTextChanged {
                courseTitleChangedListener.invoke(it.toString())
            }

            binding.etCourseDescription.doAfterTextChanged {
                courseDescriptionChangedListener.invoke(it.toString())
            }

            binding.areaDate.setOnClickListener {
                dateClickListener.invoke()
            }
        }

        fun onBind(item: CourseWriteState.Header) {
            binding.header = item
            binding.tvCourseTitle.setHtmlText(itemView.context.getString(R.string.course_title))
        }
    }
}
