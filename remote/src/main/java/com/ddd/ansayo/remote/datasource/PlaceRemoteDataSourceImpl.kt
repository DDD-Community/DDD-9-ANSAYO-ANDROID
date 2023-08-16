package com.ddd.ansayo.remote.datasource

import com.ddd.ansayo.core_model.common.Response
import com.ddd.ansayo.core_model.place.FavoritePlacesEntity
import com.ddd.ansayo.core_model.place.Place
import com.ddd.ansayo.core_model.place.SearchPlaceEntity
import com.ddd.ansayo.data.datasource.place.PlaceRemoteDataSource
import com.ddd.ansayo.remote.service.PlaceService
import com.ddd.ansayo.remote.util.toResponse
import javax.inject.Inject

class PlaceRemoteDataSourceImpl @Inject constructor(
    private val placeService: PlaceService
) : PlaceRemoteDataSource {

    override suspend fun getPlaces(query: String): Response<List<Place>> {
        return placeService.getPlaces(query).toResponse()
    }

    override suspend fun getFavoritePlaces(): Response<FavoritePlacesEntity.Response> {
        return placeService.getFavoritePlaces().toResponse()
    }

    override suspend fun postFavoritePlace(body: FavoritePlacesEntity.PostRequest): Response<Unit> {
        return placeService.postFavoritePlace(body).toResponse()
    }

    override suspend fun deleteFavoritePlace(placeId: String): Response<Unit> {
        return placeService.deleteFavoritePlace(placeId).toResponse()
    }

    override suspend fun getSearchPlace(query: String): Response<SearchPlaceEntity> {
        return placeService.getSearchCourse(query).toResponse()
    }
}
