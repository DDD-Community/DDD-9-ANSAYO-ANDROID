package com.ddd.ansayo.remote.service

import com.ddd.ansayo.core_model.course.UploadImageUrlEntity
import com.skydoves.sandwich.ApiResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Multipart
import retrofit2.http.PUT
import retrofit2.http.Part

interface FileService {

    @Multipart
    @PUT("api/upload")
    suspend fun uploadImage(
        @Part("Content-Type") fileType: RequestBody,
        @Part file: MultipartBody.Part
    ): ApiResponse<UploadImageUrlEntity>
}
