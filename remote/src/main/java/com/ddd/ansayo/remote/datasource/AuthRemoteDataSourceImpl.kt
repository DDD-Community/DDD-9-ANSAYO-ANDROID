package com.ddd.ansayo.remote.datasource

import com.ddd.ansayo.core_model.auth.AuthToken
import com.ddd.ansayo.core_model.auth.TokenInfo
import com.ddd.ansayo.core_model.common.Response
import com.ddd.ansayo.data.datasource.auth.AuthRemoteDataSource
import com.ddd.ansayo.remote.service.LoginService
import com.ddd.ansayo.remote.util.toResponse
import javax.inject.Inject

class AuthRemoteDataSourceImpl @Inject constructor(
    private val loginservice: LoginService
): AuthRemoteDataSource {
    override suspend fun signInWithSocial(params: TokenInfo): Response<AuthToken> {
        return loginservice.signInWithSocial(params).toResponse()
    }

    override suspend fun signOut() {
        loginservice.signOut()
    }

}