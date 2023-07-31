package com.ddd.ansayo.data.identity

import com.ddd.ansayo.data.api.GgecoApiInterface
import com.ddd.ansayo.data.preference.UserPref
import com.example.domain.custmoer.model.SocialProvider
import com.example.domain.custmoer.model.SocialSignInParam
import com.example.domain.identity.IdentityRepository
import com.example.domain.identity.model.TokenInfoResponse
import com.orhanobut.logger.Logger
import javax.inject.Inject

class IdentityRepositoryImpl @Inject constructor(
    private val ggecoApiInterface: GgecoApiInterface,
    private val userPref: UserPref
): IdentityRepository {

    override suspend fun signInWithSocial(
        provider: SocialProvider,
        token: String
    ): TokenInfoResponse {
        ggecoApiInterface.signInWithSocial(
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
        ggecoApiInterface.signOut()
    }

}