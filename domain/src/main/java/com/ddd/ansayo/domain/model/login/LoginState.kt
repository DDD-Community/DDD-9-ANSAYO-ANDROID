package com.ddd.ansayo.domain.model.login

import com.ddd.ansayo.core_model.auth.AuthToken

data class LoginState(
    val AuthToken: String
) {
    companion object {
        val EMPTY = LoginState (
            AuthToken = ""
        )
    }
}
