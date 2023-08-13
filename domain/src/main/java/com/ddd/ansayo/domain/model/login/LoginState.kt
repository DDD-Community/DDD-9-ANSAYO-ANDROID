package com.ddd.ansayo.domain.model.login

import com.ddd.ansayo.core_model.auth.AuthToken

data class LoginState(
    val response: AuthToken
) {
    companion object {
        val EMPTY = LoginState (
            response = AuthToken(token = "")
        )
    }
}
