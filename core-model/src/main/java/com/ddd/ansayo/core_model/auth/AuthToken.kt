package com.ddd.ansayo.core_model.auth

import com.google.gson.annotations.SerializedName

data class AuthToken(
    @SerializedName("refresh_token")
    val token: String
)
