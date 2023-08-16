package com.ddd.ansayo.domain.model.login

import com.ddd.ansayo.core_model.auth.TokenInfo

sealed class LoginMutation {

    sealed class Mutation: LoginMutation() {
        data class PostAuthToken(val tokenInfo: TokenInfo): Mutation()
    }

    sealed class SideEffect: LoginMutation() {
        data class ShowSnackBar(val message: String): SideEffect()
        object StartHomeSreen: SideEffect()

    }
}
