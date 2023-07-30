package com.ddd.ansayo.data.datasource.place

import com.ddd.ansayo.core_model.place.Place

interface PlaceRemoteDataSource {

    suspend fun getPlaces(query: String): List<Place>
}
