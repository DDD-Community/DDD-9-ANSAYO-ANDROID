package com.ddd.ansayo.core_model.course

import com.google.gson.annotations.SerializedName

data class RequestPlaceReview(
    val photos: List<PlacePhoto>,
    @SerializedName("place_id") val placeId: String,
    val review: String
)
