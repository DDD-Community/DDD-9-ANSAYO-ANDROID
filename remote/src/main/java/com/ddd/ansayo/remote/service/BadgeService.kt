package com.ddd.ansayo.remote.service

import com.ddd.ansayo.core_model.search.SearchCourseEntity
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET

interface BadgeService {
    @GET("app/badge/searchables")
    suspend fun getSearchableBadge(
    ): ApiResponse<SearchCourseEntity.Response>
}