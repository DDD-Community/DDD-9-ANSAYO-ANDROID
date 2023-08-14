package com.ddd.ansayo.data.datasource.auth

import com.ddd.ansayo.core_model.auth.AuthToken
import com.ddd.ansayo.core_model.common.Response

interface AuthRemoteDataSource {
    suspend fun signInWithSocial(
        token: String,
        kakaoAccount: Boolean,
        naverAccount: Boolean
    ): Response<AuthToken>
    suspend fun signOut()
}