package com.ddd.ansayo.core_model.course

import com.google.gson.annotations.SerializedName

data class Course(
    @SerializedName("author_badge_image") val authorBadgeImage: String,
    @SerializedName("author_badge_name") val authorBadgeName: String,
    @SerializedName("author_id") val authorId: String,
    @SerializedName("author_nickname") val authorName: String,
    val category: String,
    val favorites: Int,
    val id: String,
    @SerializedName("is_favorite") val isFavorite: Boolean,
    val name: String,
    val review: String,
    @SerializedName("reg_date") val regDate: String,
    @SerializedName("title_image") val titleImage: String,
    @SerializedName("village_address") val villageAddress: String
)
