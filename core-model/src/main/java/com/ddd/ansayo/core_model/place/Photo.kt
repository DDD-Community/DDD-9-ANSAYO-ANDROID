package com.ddd.ansayo.core_model.place

import com.google.gson.annotations.SerializedName

data class Photo(
    val height: Int,
    @SerializedName("html_attributions") val htmlAttributions: List<String>,
    @SerializedName("photo_path") val photoPath: String,
    @SerializedName("photo_reference") val photoReference: String,
    val width: Int
)
