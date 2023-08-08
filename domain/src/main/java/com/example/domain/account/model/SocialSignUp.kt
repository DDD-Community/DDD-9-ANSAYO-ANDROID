package com.example.domain.account.model

import com.ddd.ansayo.core_model.account.SocialProvider

data class SocialSignUp(
    val provider: SocialProvider,
    val token: String,
    val nickName: String
)
