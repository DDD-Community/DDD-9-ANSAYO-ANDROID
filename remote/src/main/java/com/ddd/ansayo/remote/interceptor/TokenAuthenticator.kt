package com.ddd.ansayo.remote.interceptor

import com.ddd.ansayo.data.AuthLocalDataSource
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import java.net.HttpURLConnection
import javax.inject.Inject

class TokenAuthenticator @Inject constructor(
    private val authLocalDataSource: AuthLocalDataSource
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        if (response.code == HttpURLConnection.HTTP_UNAUTHORIZED) {
            // TODO("토큰 리프레시")

            return response.request.newBuilder()
                .header("Authorization", authLocalDataSource.authToken)
                .build()
        }
        return null
    }
}
