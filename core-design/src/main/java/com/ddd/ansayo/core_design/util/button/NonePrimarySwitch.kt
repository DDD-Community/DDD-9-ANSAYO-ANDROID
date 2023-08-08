package com.ddd.ansayo.core_design.util.button

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.SwitchCompat
import androidx.core.content.ContextCompat
import com.ddd.ansayo.core_design.R

class NonePrimarySwitch @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : SwitchCompat(context, attrs) {

    init {
        thumbDrawable = ContextCompat.getDrawable(context, R.drawable.ds_switch_thumb)
        trackDrawable = ContextCompat.getDrawable(context, R.drawable.ds_switch_track)
    }
}
