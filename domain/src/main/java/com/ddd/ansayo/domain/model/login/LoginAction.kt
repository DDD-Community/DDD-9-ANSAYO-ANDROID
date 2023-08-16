package com.ddd.ansayo.domain.model.login

sealed class LoginAction {
    data class ClickKakaoLogin(val authToken: String): LoginAction()
    data class ClickNaverLogin(val authToken: String): LoginAction()
}




