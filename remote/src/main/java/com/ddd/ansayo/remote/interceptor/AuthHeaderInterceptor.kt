package com.ddd.ansayo.remote.interceptor

import com.ddd.ansayo.data.datasource.auth.AuthLocalDataSource
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthHeaderInterceptor @Inject constructor(
    private val authLocalDataSource: AuthLocalDataSource
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder().apply {
            header("Authorization", authLocalDataSource.authToken)
        }.build()
        return chain.proceed(request)
    }
}
