package com.ddd.ansayo.core_design.util.textfiled

import android.content.Context
import android.text.Editable
import android.text.InputFilter
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import androidx.core.widget.doAfterTextChanged
import com.ddd.ansayo.core_design.R
import com.ddd.ansayo.core_design.databinding.DsTextFiledLongBinding

class TextFiledLong @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet,
    defStyleAttr: Int = 0,
) : TextField(context, attributeSet, defStyleAttr) {

    override val binding = DsTextFiledLongBinding.inflate(LayoutInflater.from(context), this, true)
    override val editText: EditText = binding.etContent
    override val errorField: TextView = binding.tvWarning

    var maxLength: Int = 0
        set(value) {
            field = value
            editText.filters += arrayOf(InputFilter.LengthFilter(value))
            setTextLength(text.length)
        }

    init {
        val attrs = context.obtainStyledAttributes(attributeSet, R.styleable.TextField)
        hint = attrs.getString(R.styleable.TextField_android_hint).orEmpty()
        isInputFieldEnabled = attrs.getBoolean(R.styleable.TextField_android_enabled, true)
        errorFieldMessage = attrs.getString(R.styleable.TextField_errorFieldMessage).orEmpty()
        errorFieldVisible = attrs.getBoolean(R.styleable.TextField_errorFieldVisible, false)
        maxLength = attrs.getInt(R.styleable.TextField_android_maxLength, 0)
        attrs.recycle()

        initView()
    }

    private fun initView() {
        editText.doAfterTextChanged {
            setTextLength(it.toString().length)
        }
    }

    private fun setTextLength(length: Int) {
        binding.layoutEt.background = if (length == maxLength) {
            ContextCompat.getDrawable(context, R.drawable.button_label_secondary_error)
        } else {
            ContextCompat.getDrawable(context, R.drawable.button_label_secondary_enable)
        }
        binding.tvLength.text = when (length) {
            0 -> {
                HtmlCompat.fromHtml(
                    context.getString(R.string.length_count_init, length, maxLength),
                    HtmlCompat.FROM_HTML_MODE_COMPACT
                )
            }
            maxLength -> {
                HtmlCompat.fromHtml(
                    context.getString(R.string.length_count_max, length, maxLength),
                    HtmlCompat.FROM_HTML_MODE_COMPACT
                )
            }
            else -> {
                HtmlCompat.fromHtml(
                    context.getString(R.string.length_count, length, maxLength),
                    HtmlCompat.FROM_HTML_MODE_COMPACT
                )
            }
        }
    }

    fun doAfterTextChanged(block: (Editable?) -> Unit) {
        binding.etContent.doAfterTextChanged { block.invoke(it) }
    }
}
