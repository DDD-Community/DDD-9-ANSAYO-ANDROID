package com.ddd.ansayo.domain.handler.login

import com.ddd.ansayo.core_model.auth.TokenInfo
import com.ddd.ansayo.core_model.common.Response
import com.ddd.ansayo.domain.model.login.LoginAction
import com.ddd.ansayo.domain.model.login.LoginMutation
import com.ddd.ansayo.domain.model.login.LoginState
import com.ddd.ansayo.domain.usecase.login.SocialLoginUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
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
                    when(val result = socialLoginUseCase.invoke(
                        token = state.AuthToken,
                        kakaoAccount = true,
                        naverAccount = false
                    )) {
                        is Response.Success -> LoginMutation.SideEffect.StartHomeSreen
                        is Response.Fail -> LoginMutation.SideEffect.ShowSnackBar(result.message)

                    }
                    flowOf(
                        LoginMutation.Mutation.PostAuthToken(
                            TokenInfo(
                                accessToken = action.authToken,
                                kakaoAccount = true,
                                naverAccount = false
                            )
                        )
                    )
                }
                is LoginAction.ClickNaverLogin -> {
                    flowOf(
                        LoginMutation.Mutation.PostAuthToken(
                            TokenInfo(
                                accessToken = action.authToken,
                                kakaoAccount = false,
                                naverAccount = true
                            )
                        )
                    )

                }
            }
        }

    }

}