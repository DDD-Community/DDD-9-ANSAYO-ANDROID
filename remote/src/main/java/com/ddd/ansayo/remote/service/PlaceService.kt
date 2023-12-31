package com.ddd.ansayo.remote.service

import com.ddd.ansayo.core_model.place.FavoritePlacesEntity
import com.ddd.ansayo.core_model.place.PlaceInfoEntity
import com.ddd.ansayo.core_model.place.SearchPlaceEntity
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface PlaceService {

    @GET("/app/place/favorite")
    suspend fun getFavoritePlaces(): ApiResponse<FavoritePlacesEntity.Response>

    @POST("/app/place/favorite")
    suspend fun postFavoritePlace(
        @Body body: FavoritePlacesEntity.PostRequest
    ): ApiResponse<Unit>

    @DELETE("/app/place/favorite")
    suspend fun deleteFavoritePlace(
        @Query("place_id") placeId: String
    ): ApiResponse<Unit>

    @GET("app/place/search")
    suspend fun getSearchCourse(
        @Query("query") query: String,
    ): ApiResponse<SearchPlaceEntity>

    @GET("app/place")
    suspend fun getPlaceInfo(
        @Query("place_id") placeId: String,
        @Query("course_count") courseCount: Int
    ): ApiResponse<PlaceInfoEntity>
}
