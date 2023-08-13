package com.ddd.ansayo.domain.usecase.login

import com.ddd.ansayo.core_model.auth.AuthToken

interface KakaoUseCase {

    fun getAccessToken()

    fun signOut()
}