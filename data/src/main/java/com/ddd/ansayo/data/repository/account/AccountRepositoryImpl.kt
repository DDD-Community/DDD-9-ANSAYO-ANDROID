package com.ddd.ansayo.data.repository.account


import com.ddd.ansayo.core_model.account.SocialProvider
import com.ddd.ansayo.core_model.account.TokenInfoResponse
import com.ddd.ansayo.data.datasource.account.AccountRemoteDataSource
import com.example.domain.account.AccountRepository
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val accountRemoteDataSource: AccountRemoteDataSource
): AccountRepository {
    override suspend fun signInWithSocial(
        provider: SocialProvider,
        token: String
    ): TokenInfoResponse {
        return accountRemoteDataSource.signInWithSocial(provider, token)
    }

    override suspend fun signOut() {
        return accountRemoteDataSource.signOut()
    }

}