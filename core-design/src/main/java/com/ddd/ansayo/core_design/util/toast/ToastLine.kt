package com.ddd.ansayo.core_design.util.toast

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.ddd.ansayo.core_design.R

object ToastLine {

    private var myToast: Toast? = null

    fun toastShow(context: Context, message: String) {
        myToast?.let {
            it.view?.findViewById<TextView>(R.id.tv_message)?.text = message
        } ?: let {
            val view: View = LayoutInflater.from(context)
                .inflate(
                    R.layout.ds_toast_default,
                    (context as Activity).findViewById(R.id.container)
                )

            view.findViewById<TextView>(R.id.tv_message).text = message
            myToast = Toast(context).apply {
                duration = Toast.LENGTH_SHORT
                this.view = view
            }
        }
        myToast?.show()
    }
}