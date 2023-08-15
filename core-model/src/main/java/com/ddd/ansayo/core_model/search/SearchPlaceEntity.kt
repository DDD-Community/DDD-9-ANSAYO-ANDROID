package com.ddd.ansayo.core_model.search

import com.ddd.ansayo.core_model.place.Place

sealed class SearchPlaceEntity {
    data class Response(
        val places: List<Place>?
    ) : SearchPlaceEntity()
}
