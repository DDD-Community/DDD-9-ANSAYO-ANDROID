package com.ddd.ansayo.remote.service

import com.ddd.ansayo.core_model.auth.AuthToken
import com.ddd.ansayo.core_model.auth.TokenInfo
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("app/auth/signin")
    suspend fun signInWithSocial(
        @Body param: TokenInfo
    ): ApiResponse<AuthToken>

    @POST("user/signout")
    suspend fun signOut()
}