package com.example.domain.identity

import com.example.domain.custmoer.model.SocialProvider
import com.example.domain.identity.model.TokenInfoResponse

interface SocialLoginUseCase {

    suspend fun login(provider: SocialProvider, token: String): TokenInfoResponse

}