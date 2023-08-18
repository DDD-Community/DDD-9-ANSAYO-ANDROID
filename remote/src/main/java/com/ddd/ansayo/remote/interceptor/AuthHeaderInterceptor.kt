package com.ddd.ansayo.remote.interceptor

import com.ddd.ansayo.data.AuthLocalDataSource
import com.ddd.ansayo.remote.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthHeaderInterceptor @Inject constructor(
    private val authLocalDataSource: AuthLocalDataSource
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder().apply {
            header("x-functions-key", BuildConfig.X_FUNCTION_KEY)
            header("Authorization", "Bearer ${authLocalDataSource.authToken}")
        }.build()
        return chain.proceed(request)
    }
}
