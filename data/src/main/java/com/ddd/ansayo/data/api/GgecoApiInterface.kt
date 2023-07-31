package com.ddd.ansayo.data.api

import com.example.domain.custmoer.model.SocialSignInParam
import com.example.domain.identity.model.TokenInfoResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface GgecoApiInterface {

    @POST("link/signin")
    suspend fun signInWithSocial(@Body param: SocialSignInParam): Response<TokenInfoResponse>

    @POST("user/signout")
    suspend fun signOut()

}