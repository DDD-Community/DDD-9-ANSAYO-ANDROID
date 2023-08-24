package com.ddd.ansayo.domain.model.login


data class LoginState(
    val authToken: String
) {
    companion object {
        val EMPTY = LoginState (
            authToken = ""
        )
    }
}
