package com.ddd.ansayo.domain.model.login

sealed class LoginMutation {

    sealed class Mutation: LoginMutation() {

    }

    sealed class SideEffect: LoginMutation() {
        data class ShowSnackBar(val message: String): SideEffect()

    }
}
