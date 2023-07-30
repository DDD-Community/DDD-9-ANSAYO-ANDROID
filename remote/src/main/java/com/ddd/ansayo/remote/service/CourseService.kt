package com.ddd.ansayo.remote.service

import com.ddd.ansayo.core_model.course.UploadImageUrlEntity
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CourseService {

    @GET("/image")
    suspend fun getImageUploadUrl(
        @Query("code") code: String,
        @Query("name") name: String
    ): ApiResponse<UploadImageUrlEntity>
}
