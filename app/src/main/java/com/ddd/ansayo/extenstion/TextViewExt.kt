package com.ddd.ansayo.extenstion

import android.widget.TextView
import androidx.core.text.HtmlCompat

fun TextView.setHtmlText(str: String) {
    text = HtmlCompat.fromHtml(str, HtmlCompat.FROM_HTML_MODE_COMPACT)
}
