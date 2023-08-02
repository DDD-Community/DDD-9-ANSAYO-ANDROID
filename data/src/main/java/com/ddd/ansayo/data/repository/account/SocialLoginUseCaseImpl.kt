package com.ddd.ansayo.data.repository.account

import com.ddd.ansayo.core_model.account.SocialProvider
import com.ddd.ansayo.core_model.account.TokenInfoResponse
import com.example.domain.account.AccountRepository
import com.example.domain.account.SocialLoginUseCase
import com.orhanobut.logger.Logger
import javax.inject.Inject

class SocialLoginUseCaseImpl @Inject constructor(
    private val identityRepository: AccountRepository
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