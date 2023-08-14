package com.ddd.ansayo.domain.model.place

import com.ddd.ansayo.core_model.course.Course
import com.ddd.ansayo.core_model.place.Place

data class PlaceDetailState(
    val placeDetail: PlaceDetail,
    val courses: List<Course>
)

data class PlaceDetail(
    val place: Place,
    val favoriteCount: Int,
    val isFavorite: Boolean
)
