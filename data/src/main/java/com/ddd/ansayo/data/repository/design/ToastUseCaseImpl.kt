package com.ddd.ansayo.data.repository.design

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import com.ddd.ansayo.domain.usecase.design.ToastUseCase
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class ToastUseCaseImpl @Inject constructor(
    @ActivityContext private val context: Context
) : ToastUseCase {

    private var toast: Toast? = null

    @SuppressLint("ShowToast")
    override fun showToast(message: String) {
        toast?.cancel()
        toast = Toast.makeText(context, message, Toast.LENGTH_SHORT).apply { show() }
    }

}