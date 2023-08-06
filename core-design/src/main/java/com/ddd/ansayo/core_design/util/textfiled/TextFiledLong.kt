package com.ddd.ansayo.core_design.util.textfiled

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.ddd.ansayo.core_design.databinding.DsTextFiledLongBinding

class TextFiledLong @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding = DsTextFiledLongBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        binding.apply {

        }
    }

    fun setMaxTextLength(length: Int) {
        binding.tvLength.text = "${getText()}/ $length"
    }

    fun getText(): String {
        return binding.etContent.text.toString()
    }
}