package com.ddd.ansayo.domain.repository

import com.ddd.ansayo.core_model.place.Place

interface PlaceRepository {

    suspend fun getPlaces(query: String): List<Place>
}
