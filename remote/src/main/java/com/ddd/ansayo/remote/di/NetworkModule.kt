package com.ddd.ansayo.remote.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.ddd.ansayo.remote.interceptor.AuthHeaderInterceptor
import com.ddd.ansayo.remote.interceptor.TokenAuthenticator
import com.orhanobut.logger.Logger
import com.skydoves.sandwich.adapters.ApiResponseCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
    fun provideChuckerInterceptor(
        @ApplicationContext context: Context
    ): ChuckerInterceptor {
        return ChuckerInterceptor.Builder(context)
            .collector(
                ChuckerCollector(
                    context = context,
                    retentionPeriod = RetentionManager.Period.ONE_DAY
                )
            )
            .alwaysReadResponseBody(true)
            .createShortcut(true)
            .build()
    }

    @Provides
    @Singleton
    @AuthClient
    fun providesAuthClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        authHeaderInterceptor: AuthHeaderInterceptor,
        tokenAuthenticator: TokenAuthenticator,
        chuckerInterceptor: ChuckerInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(chuckerInterceptor)
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
    @FileUploadClient
    fun providesFileUploadClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        authHeaderInterceptor: AuthHeaderInterceptor,
        tokenAuthenticator: TokenAuthenticator,
        chuckerInterceptor: ChuckerInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(chuckerInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(authHeaderInterceptor)
            .authenticator(tokenAuthenticator)
            .connectTimeout(FILE_UPLOAD_TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(FILE_UPLOAD_TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(FILE_UPLOAD_TIME_OUT, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun providesRetrofitBuilder(): Retrofit.Builder {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
    }
}
