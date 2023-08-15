package com.example.domain.account

import com.ddd.ansayo.core_model.account.SocialProvider
import com.ddd.ansayo.core_model.account.TokenInfoResponse
import javax.inject.Inject

class SocialLoginUseCase @Inject constructor(
    private val accountRepository: AccountRepository
) {
    suspend fun login(provider: SocialProvider, token: String): TokenInfoResponse {
        try {
            return accountRepository.signInWithSocial(provider, token)
        } catch (e: Exception) {
            throw e
        }
    }
}