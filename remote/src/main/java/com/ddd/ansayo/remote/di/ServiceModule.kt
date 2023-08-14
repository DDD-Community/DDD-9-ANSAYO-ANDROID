package com.ddd.ansayo.remote.di

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
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    private const val BASE_URL = "https://ggeco-api.azurewebsites.net/api/"

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
            .client(okHttpClient)
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun providesLoginService(
        retrofitBuilder: Retrofit.Builder,
        @AuthClient okHttpClient: OkHttpClient
    ): LoginService {
        return retrofitBuilder
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create()
    }
}
