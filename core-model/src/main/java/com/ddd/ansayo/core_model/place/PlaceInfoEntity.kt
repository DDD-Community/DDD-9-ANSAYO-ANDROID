package com.ddd.ansayo.core_model.place

import com.ddd.ansayo.core_model.course.Course
import com.google.gson.annotations.SerializedName

data class PlaceInfoEntity(
    val course: List<Course>?,
    val data: Place,
    @SerializedName("favorite_count") val favoriteCount: Int
)
