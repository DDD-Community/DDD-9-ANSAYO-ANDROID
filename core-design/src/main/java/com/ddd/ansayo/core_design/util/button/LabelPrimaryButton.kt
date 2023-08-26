package com.ddd.ansayo.core_design.util.button

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import com.ddd.ansayo.core_design.R

class LabelPrimaryButton @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet,
    defStyleAttr: Int = 0,
) : AppCompatButton(context, attributeSet, defStyleAttr) {

    init {
        val attrs = context.obtainStyledAttributes(attributeSet, R.styleable.LabelPrimaryButton)
        background = ContextCompat.getDrawable(context, R.drawable.bg_button_label_primary_default)
        gravity = Gravity.CENTER
        setTextAppearance(R.style.Text_L1)
        text = attrs.getString(R.styleable.LabelPrimaryButton_android_text)
        setTextColor(ContextCompat.getColor(context, R.color.N100))
        isEnabled = attrs.getBoolean(R.styleable.LabelPrimaryButton_android_enabled, false)
        attrs.recycle()
    }
}
