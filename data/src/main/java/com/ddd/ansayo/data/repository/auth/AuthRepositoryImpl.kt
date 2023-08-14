package com.ddd.ansayo.data.repository.auth

import com.ddd.ansayo.core_model.auth.AuthToken
import com.ddd.ansayo.core_model.auth.TokenInfo
import com.ddd.ansayo.core_model.common.Response
import com.ddd.ansayo.data.datasource.auth.AuthRemoteDataSource
import com.ddd.ansayo.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authRemoteDataSource: AuthRemoteDataSource
): AuthRepository {
    override suspend fun signInWithSocial(params: TokenInfo): Response<AuthToken> {
        return authRemoteDataSource.signInWithSocial(params)
    }

    override suspend fun signOut() {
        return authRemoteDataSource.signOut()
    }


}
