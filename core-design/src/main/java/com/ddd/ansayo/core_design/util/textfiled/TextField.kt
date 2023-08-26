package com.ddd.ansayo.core_design.util.textfiled

import android.content.Context
import android.util.AttributeSet
import android.widget.EditText
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.databinding.ViewDataBinding

abstract class TextField @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attributeSet, defStyleAttr) {

    protected abstract val binding: ViewDataBinding
    protected abstract val editText: EditText
    protected abstract val errorField: TextView

    var text: String
        get() = editText.text.toString()
        set(value) {
            editText.setText(value)
        }

    var hint: String
        get() = editText.hint.toString()
        set(value) {
            editText.hint = value
        }

    var isInputFieldEnabled: Boolean
        get() = isEnabled
        set(value) {
            editText.isEnabled = value
            isEnabled = value
        }

    var errorFieldMessage: String
        get() = errorField.text.toString()
        set(value) {
            errorField.text = value
        }

    var errorFieldVisible: Boolean
        get() = errorField.isVisible
        set(value) {
            errorField.isVisible = value
        }
}
