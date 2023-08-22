package com.ddd.ansayo.core_model.place

import com.google.gson.annotations.SerializedName

data class Review(
    @SerializedName("author_name") val authorName: String,
    val language: String,
    val rating: Float,
    val text: String
)
