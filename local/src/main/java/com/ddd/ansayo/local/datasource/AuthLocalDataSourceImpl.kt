package com.ddd.ansayo.local.datasource

import com.ddd.ansayo.data.datasource.auth.AuthLocalDataSource
import javax.inject.Inject

class AuthLocalDataSourceImpl @Inject constructor(): AuthLocalDataSource {
    override val authToken: String
        get() = TODO("Not yet implemented")
}