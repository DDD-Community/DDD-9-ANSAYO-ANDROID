package com.ddd.ansayo.remote.datasource

import android.media.session.MediaSession.Token
import com.ddd.ansayo.core_model.auth.AuthToken
import com.ddd.ansayo.core_model.auth.TokenInfo
import com.ddd.ansayo.core_model.common.Response
import com.ddd.ansayo.data.datasource.auth.AuthRemoteDataSource
import com.ddd.ansayo.remote.service.LoginService
import com.ddd.ansayo.remote.util.toResponse
import com.skydoves.sandwich.getOrThrow
import javax.inject.Inject

class AuthRemoteDataSourceImpl @Inject constructor(
    private val loginservice: LoginService
): AuthRemoteDataSource {
    override suspend fun signInWithSocial(
        token: String,
        kakaoAccount: Boolean,
        naverAccount: Boolean
    ): Response<AuthToken> {
        return loginservice.signInWithSocial(
            TokenInfo(
                accessToken = token,
                kakaoAccount = kakaoAccount,
                naverAccount = naverAccount
            )
        ).toResponse()
    }

    override suspend fun signOut() {
        loginservice.signOut()
    }

}