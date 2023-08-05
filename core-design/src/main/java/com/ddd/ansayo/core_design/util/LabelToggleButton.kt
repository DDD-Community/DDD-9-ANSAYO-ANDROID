package com.ddd.ansayo.core_design.util

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.CompoundButton
import androidx.constraintlayout.widget.ConstraintLayout
import com.ddd.ansayo.core_design.databinding.DsButtonToggleDefaultBinding

class LabelToggleButton @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attributeSet, defStyleAttr) {

    private val binding: DsButtonToggleDefaultBinding = DsButtonToggleDefaultBinding.inflate(
        LayoutInflater.from(context),
        this,
        true
    )

    init {
        binding.btnSw.setOnCheckedChangeListener { _, isChecked ->
            updateToggle(isChecked)
        }
    }

    fun setOnCheckedChangeListener(listener: CompoundButton.OnCheckedChangeListener) {
        binding.btnSw.setOnCheckedChangeListener(listener)
    }

    fun isChecked(): Boolean {
        return binding.btnSw.isChecked
    }

    fun setChecked(checked: Boolean) {
        binding.btnSw.isChecked = checked
        updateToggle(checked)
    }

    private fun updateToggle(isChecked: Boolean) {
        if (isChecked) {

        } else {

        }
    }
}

