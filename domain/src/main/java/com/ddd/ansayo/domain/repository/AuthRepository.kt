package com.ddd.ansayo.domain.repository

import com.ddd.ansayo.core_model.auth.AuthToken
import com.ddd.ansayo.core_model.common.Response

interface AuthRepository {
    suspend fun signInWithSocial(token: String, kakaoAccount: Boolean, naverAccount: Boolean): Response<AuthToken>
    suspend fun signOut()
}