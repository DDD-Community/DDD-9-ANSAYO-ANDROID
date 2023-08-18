package com.ddd.ansayo.core_model.course

import com.google.gson.annotations.SerializedName

data class PlacePhoto(
    @SerializedName("origin_url")
    val originUrl: String,
    @SerializedName("thumbnail_url")
    val thumbnailUrl: String
)