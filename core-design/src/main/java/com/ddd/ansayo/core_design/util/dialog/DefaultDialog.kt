package com.ddd.ansayo.core_design.util.dialog

import android.app.Dialog
import android.content.Context
import android.view.Window
import com.ddd.ansayo.core_design.databinding.DsDialogDefaultBinding

class DefaultDialog (
    context: Context,
    contents: String,
    private val negativeButton: Pair<String, (() -> Any)?>,
    private val positiveButton: Triple<String, Int, (() -> Any)?>
) : Dialog(context) {

    private val binding: DsDialogDefaultBinding

    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        binding = DsDialogDefaultBinding.inflate(layoutInflater)

        binding.apply {

            btnNegative.apply {
                text = negativeButton.first
                setOnClickListener {
                    negativeButton.second?.invoke()
                    dismiss()
                }
            }

            btnPositive.apply {
                text = positiveButton.first
                setTextColor(positiveButton.second)
                setOnClickListener {
                    positiveButton.third?.invoke()
                    dismiss()
                }
            }
        }
        setContentView(binding.root)
    }
}