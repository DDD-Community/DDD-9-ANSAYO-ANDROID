package com.example.domain.identity

import com.example.domain.custmoer.model.SocialProvider
import com.example.domain.identity.model.TokenInfoResponse

interface IdentityRepository {

    suspend fun signInWithSocial(provider: SocialProvider, token: String): TokenInfoResponse

    suspend fun signOut()
}