package com.ddd.ansayo.domain.model.login

sealed class LoginAction {
    object ClickKakaoLogin: LoginAction()
    object ClickNaverLogin: LoginAction()
}




