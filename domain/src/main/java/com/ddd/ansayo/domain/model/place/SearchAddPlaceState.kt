package com.ddd.ansayo.domain.model.place

import com.ddd.ansayo.core_model.place.Place

data class SearchAddPlaceState(
    val places: List<Place>
) {
    companion object {
        val EMPTY = SearchAddPlaceState(
            places = emptyList()
        )
    }
}
