package com.ddd.ansayo.core_design.util.button

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.StringRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.ddd.ansayo.core_design.R
import com.ddd.ansayo.core_design.databinding.DsButtonSecondaryDefaultBinding

class LabelSecondaryButton @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attributeSet, defStyleAttr) {

    private val binding =
        DsButtonSecondaryDefaultBinding.inflate(LayoutInflater.from(context), this, true).apply {
            view = this@LabelSecondaryButton
            executePendingBindings()
        }

    private var clickEvent: (() -> Unit)? = null

    fun setOnClicked(event: () -> Unit) {
        clickEvent = event
    }

    fun onClicked() {
        clickEvent?.invoke()
    }

    fun setButtonText(text: String) {
        binding.tvContent.text = text
    }

    fun setButtonText(@StringRes textRes: Int) {
        binding.tvContent.text = context.getString(textRes)
    }

    override fun setEnabled(isEnabled: Boolean) {
        binding.root.isEnabled = isEnabled
        binding.tvContent.setBackgroundColor(
            ContextCompat.getColor(
                context,
                if (isEnabled) R.color.N90 else R.color.N50
            )
        )
    }
}