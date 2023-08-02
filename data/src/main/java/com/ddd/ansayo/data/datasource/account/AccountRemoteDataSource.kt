package com.ddd.ansayo.data.datasource.account

import com.ddd.ansayo.core_model.account.SocialProvider
import com.ddd.ansayo.core_model.account.TokenInfoResponse

interface AccountRemoteDataSource {

    suspend fun signInWithSocial(provider: SocialProvider, token: String): TokenInfoResponse

    suspend fun signOut()
}