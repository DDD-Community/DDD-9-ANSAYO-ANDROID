package com.ddd.ansayo.core_model.course

import com.ddd.ansayo.core_model.place.Place
import com.google.gson.annotations.SerializedName

data class CourseInfo(
    val course: Course,
    @SerializedName("place_photos")
    val placePhotos: List<List<PlacePhoto>>,
    @SerializedName("place_reviews")
    val placeReviews: List<PlaceReview>,
    val places: List<Place>
)
