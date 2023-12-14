package com.ddd.ansayo.remote.di

import com.ddd.ansayo.core_model.badge.Badge
import com.ddd.ansayo.remote.service.BadgeService
import com.ddd.ansayo.remote.service.CourseService
import com.ddd.ansayo.remote.service.FileService
import com.ddd.ansayo.remote.service.LoginService
import com.ddd.ansayo.remote.service.PlaceService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    private const val BASE_URL = "https://ggeco-func.azurewebsites.net/"
    private const val FILE_UPLOAD_URL = "https://ggeco-image-entry.azurewebsites.net/"

    @Provides
    @Singleton
    fun providesLoginService(
        retrofitBuilder: Retrofit.Builder,
        @AuthClient okHttpClient: OkHttpClient
    ): LoginService {
        return retrofitBuilder
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun providesCourseService(
        retrofitBuilder: Retrofit.Builder,
        @AuthClient okHttpClient: OkHttpClient
    ): CourseService {
        return retrofitBuilder
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun providesPlaceService(
        retrofitBuilder: Retrofit.Builder,
        @AuthClient okHttpClient: OkHttpClient
    ): PlaceService {
        return retrofitBuilder
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun providesFileUploadService(
        retrofitBuilder: Retrofit.Builder,
        @FileUploadClient okHttpClient: OkHttpClient
    ): FileService {
        return retrofitBuilder
            .baseUrl(FILE_UPLOAD_URL)
            .client(okHttpClient)
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun providesBadgeService(
        retrofitBuilder: Retrofit.Builder,
        @AuthClient okHttpClient: OkHttpClient
    ): BadgeService {
        return retrofitBuilder
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()
            .create()
    }
}
