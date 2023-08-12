package com.ddd.ansayo.core_model.place

import com.google.gson.annotations.SerializedName

data class OpeningHour(
    @SerializedName("close_day") val closeDay: String,
    @SerializedName("close_time") val closeTime: String,
    @SerializedName("open_day") val openDay: String,
    @SerializedName("open_time") val openTime: String
)
