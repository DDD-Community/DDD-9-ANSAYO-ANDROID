package com.ddd.ansayo.remote.service

import com.ddd.ansayo.core_model.account.SocialSignInParam
import com.ddd.ansayo.core_model.account.TokenInfoResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("link/signin")
    suspend fun signInWithSocial(@Body param: SocialSignInParam): Response<TokenInfoResponse>

    @POST("user/signout")
    suspend fun signOut()
}