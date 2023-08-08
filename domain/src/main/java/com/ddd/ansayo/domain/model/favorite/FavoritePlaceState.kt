package com.ddd.ansayo.domain.model.favorite

import com.ddd.ansayo.core_model.place.Place

data class FavoritePlaceState(
    val hasFavorites: Boolean,
    val places: List<Place>
)
