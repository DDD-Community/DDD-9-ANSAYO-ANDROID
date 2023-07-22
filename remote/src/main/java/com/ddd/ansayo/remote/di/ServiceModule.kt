package com.ddd.ansayo.remote.di

import com.ddd.ansayo.remote.service.CourseService
import com.ddd.ansayo.remote.service.FileService
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
    fun providesFileUploadService(
        retrofitBuilder: Retrofit.Builder,
        @FileUploadClient okHttpClient: OkHttpClient
    ): FileService {
        return retrofitBuilder
            .client(okHttpClient)
            .build()
            .create()
    }
}
