package com.ddd.ansayo.data.datasource.place

import com.ddd.ansayo.core_model.place.FavoritePlacesEntity
import com.ddd.ansayo.core_model.place.Place

interface PlaceRemoteDataSource {

    suspend fun getPlaces(query: String): List<Place>
    suspend fun getFavoritePlaces(): FavoritePlacesEntity.Response
    suspend fun postFavoritePlace(body: FavoritePlacesEntity.PostRequest)
    suspend fun deleteFavoritePlace(placeId: String)
}
