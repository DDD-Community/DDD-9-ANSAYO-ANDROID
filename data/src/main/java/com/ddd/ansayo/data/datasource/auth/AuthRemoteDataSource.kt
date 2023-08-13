package com.ddd.ansayo.data.datasource.auth

import com.ddd.ansayo.core_model.auth.AuthToken

interface AuthRemoteDataSource {
    suspend fun signInWithSocial(
        token: String,
        kakaoAccount: Boolean,
        naverAccount: Boolean
    ): AuthToken
    suspend fun signOut()
}