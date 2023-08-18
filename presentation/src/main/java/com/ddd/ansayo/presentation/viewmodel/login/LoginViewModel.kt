package com.ddd.ansayo.presentation.viewmodel.login

import android.media.session.MediaSession.Token
import androidx.lifecycle.ViewModel
import com.ddd.ansayo.core_model.auth.AuthToken
import com.ddd.ansayo.core_model.auth.TokenInfo
import com.ddd.ansayo.domain.handler.login.LoginMutationHandler
import com.ddd.ansayo.domain.model.login.LoginAction
import com.ddd.ansayo.domain.model.login.LoginMutation
import com.ddd.ansayo.domain.model.login.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginMutationHandler: LoginMutationHandler,
): ContainerHost<LoginState, LoginMutation.SideEffect>, ViewModel() {

    override val container: Container<LoginState, LoginMutation.SideEffect> =
        container(LoginState.EMPTY)

    fun onAction(action: LoginAction) = intent {
        loginMutationHandler.mutate(state, action)
            .collect { mutation ->
                when(mutation) {
                    is LoginMutation.Mutation -> reduce(mutation)
                    is LoginMutation.SideEffect -> postSideEffect(mutation)
                }
            }
        }

    private fun reduce(mutation: LoginMutation.Mutation) {
        intent {
            reduce {
                when(mutation) {
                    is LoginMutation.Mutation.PostAuthToken -> {
                        state.copy(
                            AuthToken = mutation.tokenInfo.accessToken
                        )
                    }
                }

            }
        }
    }
}