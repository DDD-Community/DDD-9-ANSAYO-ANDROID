package com.ddd.ansayo.core_model.course

import com.google.gson.annotations.SerializedName

sealed class CourseUploadEntity {
    data class Request(
        val date: String,
        @SerializedName("is_public") val isPublic: Boolean,
        val name: String,
        @SerializedName("place_review") val placeReviews: List<RequestPlaceReview>,
        val review: String
    ) : CourseUploadEntity()

    data class Response(val id: String) : CourseUploadEntity()
}
