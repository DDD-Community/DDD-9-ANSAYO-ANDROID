package com.ddd.ansayo.core_model.account

import com.google.gson.annotations.SerializedName

data class TokenInfoResponse(
    @SerializedName("data")
    val `data`: TokenInfo
)
