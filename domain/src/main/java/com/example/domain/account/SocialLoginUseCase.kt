package com.example.domain.account

import com.ddd.ansayo.core_model.account.SocialProvider
import com.ddd.ansayo.core_model.account.TokenInfoResponse


interface SocialLoginUseCase {

    suspend fun login(provider: SocialProvider, token: String): TokenInfoResponse

}