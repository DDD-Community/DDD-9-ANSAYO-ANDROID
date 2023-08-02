package com.ddd.ansayo.remote.datasource

import com.ddd.ansayo.core_model.account.SocialProvider
import com.ddd.ansayo.core_model.account.SocialSignInParam
import com.ddd.ansayo.core_model.account.TokenInfoResponse
import com.ddd.ansayo.data.datasource.account.AccountRemoteDataSource
import com.ddd.ansayo.local.preference.UserPref
import com.ddd.ansayo.remote.service.LoginService
import com.orhanobut.logger.Logger
import javax.inject.Inject

class AccountRemoteDataSourceImpl @Inject constructor(
    private val loginservice: LoginService,
    private val userPref: UserPref
): AccountRemoteDataSource {

    override suspend fun signInWithSocial(
        provider: SocialProvider,
        token: String
    ): TokenInfoResponse {
        loginservice.signInWithSocial(
            SocialSignInParam(
                provider = provider.toString(),
                token = token
            )
        ).apply {
            val code = code()
            errorBody()?.run{
                when (code) {
                    401 -> {

                    }

                    403 -> {

                    }

                    else -> Logger.d("$code 알 수 없는 에러 입니다.")

                }
            }
            return body()!!.also {
                userPref.setAccessToken(it.data.accessToken)
                userPref.setTokenType(it.data.tokenType)
            }
        }
    }

    override suspend fun signOut() {
        loginservice.signOut()
    }

}