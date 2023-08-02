package com.example.domain.account

import com.ddd.ansayo.core_model.account.SocialProvider
import com.ddd.ansayo.core_model.account.TokenInfoResponse

interface AccountRepository {

    suspend fun signInWithSocial(provider: SocialProvider, token: String): TokenInfoResponse

    suspend fun signOut()
}