package com.ddd.ansayo.core_model.auth.model

import java.util.Date

/**
 * 스키마보고 만든 임시 User
 */
data class User(
    val nickName: String,
    val createdAt: Date,
    val lastSign: Date,
    val badge: String,
    val age: Int,
    val gender: String
)
