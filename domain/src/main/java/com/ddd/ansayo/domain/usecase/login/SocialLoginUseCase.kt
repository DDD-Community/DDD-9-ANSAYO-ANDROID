package com.ddd.ansayo.domain.usecase.login

import com.ddd.ansayo.core_model.auth.AuthToken
import com.ddd.ansayo.core_model.auth.TokenInfo
import com.ddd.ansayo.core_model.common.Response
import com.ddd.ansayo.domain.repository.AuthRepository
import javax.inject.Inject

class SocialLoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(param: TokenInfo): Response<AuthToken> {
        return authRepository.signInWithSocial(param)
    }
}