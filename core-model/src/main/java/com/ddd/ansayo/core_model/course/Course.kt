package com.ddd.ansayo.core_model.course

import com.google.gson.annotations.SerializedName

data class Course(
    @SerializedName("author_id") val authorId: String,
    val favorites: Int,
    val id: String,
    @SerializedName("is_favorite") val isFavorite: Boolean,
    val name: String,
    @SerializedName("reg_date") val regDate: String,
    val review: String
)
