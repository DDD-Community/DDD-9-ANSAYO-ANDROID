package com.ddd.ansayo.domain.handler.login

import com.ddd.ansayo.core_model.common.Response
import com.ddd.ansayo.domain.model.login.LoginAction
import com.ddd.ansayo.domain.model.login.LoginMutation
import com.ddd.ansayo.domain.model.login.LoginState
import com.ddd.ansayo.domain.usecase.login.KakaoUseCase
import com.ddd.ansayo.domain.usecase.login.NaverUseCase
import com.ddd.ansayo.domain.usecase.login.SocialLoginUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginMutationHandler @Inject constructor(
    private val socialLoginUseCase: SocialLoginUseCase,
) {
    suspend fun mutate(
        state: LoginState,
        action: LoginAction
    ): Flow<LoginMutation> {
        return flow {
            when(action) {
                is LoginAction.ClickKakaoLogin -> {

                }
                is LoginAction.ClickNaverLogin -> {

                }
            }
        }

    }

}