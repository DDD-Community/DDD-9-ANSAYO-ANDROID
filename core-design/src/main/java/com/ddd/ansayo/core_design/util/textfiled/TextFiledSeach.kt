package com.ddd.ansayo.core_design.util.textfiled

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.ddd.ansayo.core_design.databinding.DsTextFiledSearchBinding

class TextFiledSeach @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding = DsTextFiledSearchBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        binding.apply {

        }
    }

    fun setHint(hint: String) {
        binding.etContent.hint = hint
    }

}