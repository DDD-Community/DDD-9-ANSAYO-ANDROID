package com.ddd.ansayo.domain.model.search

import com.ddd.ansayo.core_model.place.Place

data class SearchPlaceState(
    val places: List<Place>
) {
    companion object {
        val EMPTY = SearchPlaceState(
               places = emptyList()
        )
    }
}
