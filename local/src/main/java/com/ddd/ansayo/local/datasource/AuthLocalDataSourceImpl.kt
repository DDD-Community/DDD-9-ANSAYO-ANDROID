package com.ddd.ansayo.local.datasource

import com.ddd.ansayo.data.AuthLocalDataSource
import javax.inject.Inject

class AuthLocalDataSourceImpl @Inject constructor(): AuthLocalDataSource {
    override val authToken: String
        get() = ""
}
