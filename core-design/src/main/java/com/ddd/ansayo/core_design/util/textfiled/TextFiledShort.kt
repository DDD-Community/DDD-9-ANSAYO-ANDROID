package com.ddd.ansayo.core_design.util.textfiled

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.ddd.ansayo.core_design.R
import com.ddd.ansayo.core_design.databinding.DsTextFiledShortBinding

class TextFiledShort @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding = DsTextFiledShortBinding.inflate(LayoutInflater.from(context), this, true).apply {
        view = this@TextFiledShort
        executePendingBindings()
    }

    fun setHint(text: String) {
        binding.etContent.hint = text
    }

    fun getTextLength(text: String?) {
        binding.tvLength.text = text?.let {
            "${it.length} / 40"
        }
    }
}