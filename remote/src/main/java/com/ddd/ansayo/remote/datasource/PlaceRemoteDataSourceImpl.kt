package com.ddd.ansayo.remote.datasource

import com.ddd.ansayo.core_model.place.FavoritePlacesEntity
import com.ddd.ansayo.core_model.place.Place
import com.ddd.ansayo.data.datasource.place.PlaceRemoteDataSource
import com.ddd.ansayo.remote.service.PlaceService
import com.skydoves.sandwich.getOrThrow
import javax.inject.Inject

class PlaceRemoteDataSourceImpl @Inject constructor(
    private val placeService: PlaceService
): PlaceRemoteDataSource {

    override suspend fun getPlaces(query: String): List<Place> {
        return placeService.getPlaces(query).getOrThrow()
    }

    override suspend fun getFavoritePlaces(): FavoritePlacesEntity.Response {
        return placeService.getFavoritePlaces().getOrThrow()
    }

    override suspend fun postFavoritePlace(body: FavoritePlacesEntity.PostRequest) {
        return placeService.postFavoritePlace(body).getOrThrow()
    }

    override suspend fun deleteFavoritePlace(placeId: String) {
        return placeService.deleteFavoritePlace(placeId).getOrThrow()
    }
}
