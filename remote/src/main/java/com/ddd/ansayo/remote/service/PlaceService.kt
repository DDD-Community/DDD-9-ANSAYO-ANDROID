package com.ddd.ansayo.remote.service

import com.ddd.ansayo.core_model.place.Place
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PlaceService {

    @GET("/places")
    suspend fun getPlaces(
        @Query("query") query: String
    ): ApiResponse<List<Place>>
}
