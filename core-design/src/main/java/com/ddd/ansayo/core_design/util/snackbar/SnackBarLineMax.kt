package com.ddd.ansayo.core_design.util.snackbar

import android.view.LayoutInflater
import android.view.View
import androidx.core.content.ContextCompat
import com.ddd.ansayo.core_design.databinding.DsSnackLineBinding
import com.google.android.material.snackbar.Snackbar

class SnackBarLineMax (view: View, private val message: String) {

    private val context = view.context
    private val snackbar = Snackbar.make(view, "", Snackbar.LENGTH_SHORT)
    private val snackbarLayout = snackbar.view as Snackbar.SnackbarLayout

    private val inflater = LayoutInflater.from(context)
    val snackbarBinding = DsSnackLineBinding.inflate(inflater)
    init {
        initView()
        initData()
    }

    private fun initView() {
        with(snackbarLayout) {
            removeAllViews()
            setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent))
            addView(snackbarBinding.root, 0)
        }
    }

    private fun initData() {
        snackbarBinding.tvMessage.text = message
    }

    fun show() {
        snackbar.show()
    }
}