package com.ddd.ansayo.data.datasource.auth.identity

import android.media.session.MediaSession.Token
import com.ddd.ansayo.data.datasource.auth.api.GgecoApiInterface
import com.ddd.ansayo.data.datasource.auth.preference.UserPref
import com.example.domain.custmoer.model.SocialProvider
import com.example.domain.custmoer.model.SocialSignInParam
import com.example.domain.identity.model.SocialSignUp
import com.example.domain.identity.model.TokenInfo
import com.example.domain.identity.model.TokenInfoResponse
import com.orhanobut.logger.Logger
import javax.inject.Inject

class IdentityRepositoryImpl @Inject constructor(
    private val ggecoApiInterface: GgecoApiInterface,
    private val userPref: UserPref
): IdentityRepository {

    override suspend fun signInWithSocial(provider: SocialProvider, token: String): TokenInfoResponse {
        ggecoApiInterface.signInWithSocial(
            SocialSignInParam(
                provider = provider.toString(),
                token = token
            )
        ).apply {
            val code = code()
            when(code) {
                401 -> {

                }
                403 ->{

                }
                else -> Logger.d("$code 알 수 없는 에러 입니다.")

            }
            return body()?.also {
                userPref.setAccessToken(it.data.accessToken)
                userPref.setTokenType(it.data.tokenType)
            } ?: TokenInfoResponse(data = TokenInfo("","",""))
        }
    }

    override suspend fun signUpWithSocial(signUp: SocialSignUp): TokenInfoResponse {
        TODO("Not yet implemented")
    }

    override suspend fun signOut() {
        ggecoApiInterface.signOut()
    }

}