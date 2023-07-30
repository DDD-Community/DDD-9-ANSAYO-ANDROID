package com.example.domain.identity.model

data class TokenInfo(
    val tokenType: String,
    val accessToken: String,
    val refreshToken: String?,
)
