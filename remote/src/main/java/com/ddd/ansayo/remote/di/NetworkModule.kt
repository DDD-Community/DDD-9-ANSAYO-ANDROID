package com.ddd.ansayo.remote.di

import com.ddd.ansayo.remote.interceptor.AuthHeaderInterceptor
import com.ddd.ansayo.remote.interceptor.TokenAuthenticator
import com.orhanobut.logger.Logger
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val COMMON_TIME_OUT = 10L
    private const val FILE_UPLOAD_TIME_OUT = 30L


    @Provides
    @Singleton
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor { message -> Logger.d(message) }
    }

    @Provides
    @Singleton
    fun providesAuthClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        authHeaderInterceptor: AuthHeaderInterceptor,
        tokenAuthenticator: TokenAuthenticator
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(authHeaderInterceptor)
            .authenticator(tokenAuthenticator)
            .connectTimeout(COMMON_TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(COMMON_TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(COMMON_TIME_OUT, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun providesNoAuthClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(COMMON_TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(COMMON_TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(COMMON_TIME_OUT, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun providesFileUploadClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        authHeaderInterceptor: AuthHeaderInterceptor,
        tokenAuthenticator: TokenAuthenticator
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(authHeaderInterceptor)
            .authenticator(tokenAuthenticator)
            .connectTimeout(FILE_UPLOAD_TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(FILE_UPLOAD_TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(FILE_UPLOAD_TIME_OUT, TimeUnit.SECONDS)
            .build()
    }
}
