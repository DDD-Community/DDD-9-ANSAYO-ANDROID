package com.ddd.ansayo.core_design.util

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.findViewTreeLifecycleOwner
import com.ddd.ansayo.core_design.databinding.LayoutSearchViewBinding

class SearchView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attributeSet, defStyleAttr) {

    private val _searchText = MutableLiveData<String>()
    val searchText: LiveData<String> = _searchText

    private val _hintText = MutableLiveData<String>("검색해 주세요.")
    val hintText: LiveData<String> = _hintText


    private var changedTextCallback: ((String) -> Unit)? = null
    private var clearTextCallback: (() -> Unit)? = null
    private var focusCallback: (() -> Unit)? = null

    private val binding =
        LayoutSearchViewBinding.inflate(LayoutInflater.from(context), this, true).apply {
            view = this@SearchView
        }

    init {
        setAddTextChangedListener()
        setOnEditorActionListener()
        setFocusChangeListener()
    }

    private fun setAddTextChangedListener() {
        binding.etSearchKeyword.addTextChangedListener {
            it?.let { editable -> changedTextCallback?.invoke(editable.toString()) }
        }
    }
    private fun setOnEditorActionListener() {
        binding.etSearchKeyword.setOnEditorActionListener { textView, actionId, _ ->
            when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    binding.etSearchKeyword.clearFocus()
                    hideKeyboard()
                    _searchText.value = textView.text.toString()
                    true
                }

                else -> false
            }
        }
    }


    private fun setFocusChangeListener() {
        binding.etSearchKeyword.setOnFocusChangeListener { _, isFocus ->
            if (isFocus) {
                focusCallback?.invoke()
            }
        }
    }

    fun setText(text: String) {
        binding.etSearchKeyword.setText(text)
        binding.etSearchKeyword.setSelection(text.length)
    }

    fun showKeyboard() {
        binding.etSearchKeyword.requestFocus()
        val imm = ContextCompat.getSystemService(context, InputMethodManager::class.java)
        imm?.showSoftInput(binding.etSearchKeyword, InputMethodManager.SHOW_IMPLICIT)
    }

    fun hideKeyboard() {
        val imm = ContextCompat.getSystemService(context, InputMethodManager::class.java)
        imm?.hideSoftInputFromWindow(
            binding.etSearchKeyword.windowToken,
            InputMethodManager.HIDE_NOT_ALWAYS
        )
    }

    fun setChangedTextCallback(callback: (String) -> Unit) {
        changedTextCallback = callback
    }

    fun setFocusCallback(callback: () -> Unit) {
        focusCallback = callback
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        binding.lifecycleOwner = findViewTreeLifecycleOwner()
    }

    override fun onDetachedFromWindow() {
        changedTextCallback = null
        clearTextCallback = null
        focusCallback = null
        super.onDetachedFromWindow()
    }
}