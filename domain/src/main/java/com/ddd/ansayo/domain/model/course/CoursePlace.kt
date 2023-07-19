package com.ddd.ansayo.domain.model.course

data class CoursePlace(
    val order: Int,
    val title: String,
    val address: String,
    val category: String,
    val isPlaceReviewMaxInputted: Boolean,
    val images: List<CoursePlaceImage>
)
