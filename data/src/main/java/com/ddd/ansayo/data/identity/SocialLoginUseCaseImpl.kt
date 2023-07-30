package com.ddd.ansayo.data.identity

import com.example.domain.custmoer.model.SocialProvider
import com.example.domain.identity.IdentityRepository
import com.example.domain.identity.SocialLoginUseCase
import com.example.domain.identity.model.TokenInfoResponse
import com.orhanobut.logger.Logger
import javax.inject.Inject

class SocialLoginUseCaseImpl @Inject constructor(
    private val identityRepository: IdentityRepository
): SocialLoginUseCase {

    override suspend fun login(provider: SocialProvider, token: String): TokenInfoResponse {
        try {
            return identityRepository.signInWithSocial(provider, token)
        } catch (e: Exception) {
            Logger.d(e.toString())
            throw e
        }
    }
}