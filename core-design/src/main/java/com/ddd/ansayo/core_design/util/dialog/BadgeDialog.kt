package com.ddd.ansayo.core_design.util.dialog

import android.app.Dialog
import android.content.Context
import android.view.Window
import com.bumptech.glide.Glide
import com.ddd.ansayo.core_design.R
import com.ddd.ansayo.core_design.databinding.DsDialogBadgeBinding

class BadgeDialog (
    context: Context,
    image: String,
    title: String,
    contents: String,
    contentsDate: String,
    private val button: Pair<String, (() -> Any)?>
    ) : Dialog(context) {

        private val binding: DsDialogBadgeBinding

        init {
            requestWindowFeature(Window.FEATURE_NO_TITLE)

            binding = DsDialogBadgeBinding.inflate(layoutInflater)

            binding.apply {
                tvBadgeTitle.text = title
                tvBadgeContext.text = contents
                tvDate.text = contentsDate
                Glide.with(context)
                    .load(image)
                    .error(R.drawable.ic_tem_badge_error)
                    .into(ivBadge)
                btnConfirm.apply {
                    text = button.first
                    setOnClickListener {
                        button.second?.invoke()
                        dismiss()
                    }
                }
            }

            setContentView(binding.root)
        }
    }