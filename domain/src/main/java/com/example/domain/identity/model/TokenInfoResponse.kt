package com.example.domain.identity.model

import com.google.gson.annotations.SerializedName


data class TokenInfoResponse(
    @SerializedName("data")
    val `data`: TokenInfo
)
