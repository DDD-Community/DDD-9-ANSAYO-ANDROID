package com.ddd.ansayo.core_model.place

import com.google.gson.annotations.SerializedName

data class Place(
    @SerializedName("business_status") val businessStatus: String,
    @SerializedName("formatted_address") val formattedAddress: String,
    @SerializedName("geometry_location") val geometry: Geometry,
    val name: String,
    @SerializedName("opening_hours") val openingHours: List<OpeningHour>?,
    val phone: String,
    val photos: List<Photo>,
    @SerializedName("place_id") val placeId: String,
    val rating: Float,
    val reviews: List<Review>,
    val types: List<String>,
    @SerializedName("is_favorite") val isFavorite: Boolean
)
