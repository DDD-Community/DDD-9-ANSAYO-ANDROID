package com.ddd.ansayo.core_model.place

import com.google.gson.annotations.SerializedName

data class Place(
    @SerializedName("business_status") val businessStatus: String,
    @SerializedName("formatted_address") val formattedAddress: String,
    val geometry: PlaceGeometry,
    val name: String,
    val photos: List<PlacePhoto>,
    @SerializedName("place_id") val placeId: String,
    val rating: Int,
    val types: List<String>?,
    @SerializedName("user_ratings_total") val userRatingsTotal: Int
)
