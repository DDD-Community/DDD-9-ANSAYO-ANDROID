package com.ddd.ansayo.core_model.badge

import com.google.gson.annotations.SerializedName

data class Badge(
    @SerializedName("active_image") val activeImage: String,
    @SerializedName("inactive_image") val inactiveImage: String,
    val id: String,
    @SerializedName("selected_image") val selectedImage: String,
    val name: String,
    val summary: String
)
