package com.ddd.ansayo.data.repository.place

import com.ddd.ansayo.core_model.place.FavoritePlacesEntity
import com.ddd.ansayo.core_model.place.Place
import com.ddd.ansayo.data.datasource.place.PlaceRemoteDataSource
import com.ddd.ansayo.domain.repository.PlaceRepository
import javax.inject.Inject

class PlaceRepositoryImpl @Inject constructor(
    private val placeRemoteDataSource: PlaceRemoteDataSource
) : PlaceRepository{

    override suspend fun getPlaces(query: String): List<Place> {
        return placeRemoteDataSource.getPlaces(query)
    }

    override suspend fun getFavoritePlaces(): FavoritePlacesEntity.Response {
        return placeRemoteDataSource.getFavoritePlaces()
    }

    override suspend fun postFavoritePlace(body: FavoritePlacesEntity.PostRequest) {
        return placeRemoteDataSource.postFavoritePlace(body)
    }

    override suspend fun deleteFavoritePlace(placeId: String) {
        return placeRemoteDataSource.deleteFavoritePlace(placeId)
    }
}
