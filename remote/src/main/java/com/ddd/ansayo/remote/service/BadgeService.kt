package com.ddd.ansayo.remote.service

import com.ddd.ansayo.core_model.badge.BadgeEntity
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET

interface BadgeService {
    @GET("app/badge/searchables")
    suspend fun getSearchableBadge(
    ): ApiResponse<BadgeEntity.Response>
}