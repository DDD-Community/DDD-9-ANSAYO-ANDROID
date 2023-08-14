package com.ddd.ansayo.data.datasource.place

import com.ddd.ansayo.core_model.common.Response
import com.ddd.ansayo.core_model.place.FavoritePlacesEntity
import com.ddd.ansayo.core_model.place.Place

interface PlaceRemoteDataSource {

    suspend fun getPlaces(query: String): Response<List<Place>>
    suspend fun getFavoritePlaces(): Response<FavoritePlacesEntity.Response>
    suspend fun postFavoritePlace(body: FavoritePlacesEntity.PostRequest): Response<Unit>
    suspend fun deleteFavoritePlace(placeId: String): Response<Unit>
}
