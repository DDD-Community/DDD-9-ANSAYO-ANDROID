package com.ddd.ansayo.core_model.account

data class TokenInfo(
    val tokenType: String,
    val accessToken: String,
    val refreshToken: String?
)
