package com.ddd.ansayo.core_model.place

import java.io.Serializable

data class AddPlaceInfoDto(
    val formattedAddress: String,
    val name: String,
    val placeId: String,
    val types: List<String>?,
) : Serializable

fun Place.mapToDto(): AddPlaceInfoDto {
    return AddPlaceInfoDto(
        formattedAddress = formattedAddress,
        name = name,
        placeId = placeId,
        types = types
    )
}
