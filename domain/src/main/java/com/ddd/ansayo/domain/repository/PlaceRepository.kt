package com.ddd.ansayo.domain.repository

import com.ddd.ansayo.core_model.common.Response
import com.ddd.ansayo.core_model.place.FavoritePlacesEntity
import com.ddd.ansayo.core_model.place.Place
import com.ddd.ansayo.core_model.place.SearchPlaceEntity
import com.ddd.ansayo.core_model.place.PlaceInfoEntity

interface PlaceRepository {

    suspend fun getFavoritePlaces(): Response<FavoritePlacesEntity.Response>
    suspend fun postFavoritePlace(body: FavoritePlacesEntity.PostRequest): Response<Unit>
    suspend fun deleteFavoritePlace(placeId: String): Response<Unit>
    suspend fun getSearchPlaces(query: String): Response<SearchPlaceEntity>
    suspend fun getPlaceInfo(placeId: String, courseCount: Int) : Response<PlaceInfoEntity>
}
