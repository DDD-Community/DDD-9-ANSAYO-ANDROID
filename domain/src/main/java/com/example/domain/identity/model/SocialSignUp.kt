package com.example.domain.identity.model

import com.example.domain.custmoer.model.SocialProvider

data class SocialSignUp(
    val provider: SocialProvider,
    val token: String,
    val nickName: String
)
