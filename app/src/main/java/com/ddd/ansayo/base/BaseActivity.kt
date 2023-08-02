package com.ddd.ansayo.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<T : ViewBinding>(
    private val inflater: (LayoutInflater) -> T
) : AppCompatActivity() {

    private lateinit var _binding: T
    protected val binding: T
        get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = inflater(layoutInflater)
        setContentView(binding.root)
    }
}
