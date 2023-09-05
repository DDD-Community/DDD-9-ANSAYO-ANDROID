package com.ddd.ansayo.core_model.course

import com.google.gson.annotations.SerializedName

data class UploadImageUrlEntity(
    @SerializedName("origin_image_url") val originUrl: String,
    @SerializedName("thumbnail_image_url") val thumbnailUrl: String
)
