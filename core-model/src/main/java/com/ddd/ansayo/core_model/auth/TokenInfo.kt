package com.ddd.ansayo.core_model.auth

data class TokenInfo(
    val accessToken: String,
    val kakaoAccount: Boolean,
    val naverAccount: Boolean
)
