package com.ddd.ansayo.domain.repository

import com.ddd.ansayo.core_model.place.FavoritePlacesEntity
import com.ddd.ansayo.core_model.place.Place

interface PlaceRepository {

    suspend fun getPlaces(query: String): List<Place>
    suspend fun getFavoritePlaces(): FavoritePlacesEntity.Response
    suspend fun postFavoritePlace(body: FavoritePlacesEntity.PostRequest)
    suspend fun deleteFavoritePlace(placeId: String)
}
