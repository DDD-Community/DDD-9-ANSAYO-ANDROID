package com.ddd.ansayo.presentation.viewmodel.model

import android.os.Parcelable
import com.ddd.ansayo.core_model.place.Place
import kotlinx.parcelize.Parcelize

@Parcelize
data class AddPlaceInfoDto(
    val formattedAddress: String,
    val name: String,
    val placeId: String,
    val types: List<String>?,
) : Parcelable

fun Place.mapToDto(): AddPlaceInfoDto {
    return AddPlaceInfoDto(
        formattedAddress = formattedAddress,
        name = name,
        placeId = placeId,
        types = types
    )
}
