package com.ddd.ansayo.core_model.place

import com.google.gson.annotations.SerializedName

sealed class FavoritePlacesEntity {

    data class PostRequest(
        @SerializedName("place_id") val placeId: String
    ) : FavoritePlacesEntity()

    data class Response(
        val places: List<Place>?
    ) : FavoritePlacesEntity()
}
