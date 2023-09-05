package com.ddd.ansayo.data.repository.place

import com.ddd.ansayo.core_model.common.Response
import com.ddd.ansayo.core_model.place.FavoritePlacesEntity
import com.ddd.ansayo.core_model.place.Place
import com.ddd.ansayo.core_model.place.SearchPlaceEntity
import com.ddd.ansayo.core_model.place.PlaceInfoEntity
import com.ddd.ansayo.data.datasource.place.PlaceRemoteDataSource
import com.ddd.ansayo.domain.repository.PlaceRepository
import javax.inject.Inject

class PlaceRepositoryImpl @Inject constructor(
    private val placeRemoteDataSource: PlaceRemoteDataSource
) : PlaceRepository {

    override suspend fun getFavoritePlaces(): Response<FavoritePlacesEntity.Response> {
        return placeRemoteDataSource.getFavoritePlaces()
    }

    override suspend fun postFavoritePlace(body: FavoritePlacesEntity.PostRequest): Response<Unit> {
        return placeRemoteDataSource.postFavoritePlace(body)
    }

    override suspend fun deleteFavoritePlace(placeId: String): Response<Unit> {
        return placeRemoteDataSource.deleteFavoritePlace(placeId)
    }

    override suspend fun getSearchPlaces(query: String): Response<SearchPlaceEntity> {
        return placeRemoteDataSource.getSearchPlace(query)
    }

    override suspend fun getPlaceInfo(
        placeId: String,
        courseCount: Int
    ): Response<PlaceInfoEntity> {
        return placeRemoteDataSource.getPlaceInfo(placeId, courseCount)
    }
}
