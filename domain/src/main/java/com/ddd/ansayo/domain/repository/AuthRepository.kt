package com.ddd.ansayo.domain.repository

import com.ddd.ansayo.core_model.auth.AuthToken
import com.ddd.ansayo.core_model.auth.TokenInfo
import com.ddd.ansayo.core_model.common.Response

interface AuthRepository {
    suspend fun signInWithSocial(params: TokenInfo): Response<AuthToken>
    suspend fun signOut()
}