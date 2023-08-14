package com.ddd.ansayo.core_model.auth

import com.google.gson.annotations.SerializedName

data class TokenInfo(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("kakao_account")
    val kakaoAccount: Boolean,
    @SerializedName("naver_account")
    val naverAccount: Boolean
)
