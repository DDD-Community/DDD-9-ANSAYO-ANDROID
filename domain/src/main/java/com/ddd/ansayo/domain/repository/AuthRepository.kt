package com.ddd.ansayo.domain.repository

import com.ddd.ansayo.core_model.auth.AuthToken

interface AuthRepository {
    suspend fun signInWithSocial(token: String, kakaoAccount: Boolean, naverAccount: Boolean): AuthToken
    suspend fun signOut()
}