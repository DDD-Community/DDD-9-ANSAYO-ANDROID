package com.ddd.ansayo.data.repository.auth

import android.content.Context
import com.ddd.ansayo.domain.usecase.login.NaverUseCase
import com.navercorp.nid.NaverIdLoginSDK
import com.navercorp.nid.oauth.OAuthLoginCallback
import com.orhanobut.logger.Logger
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class NaverUseCaseImpl @Inject constructor(
    @ActivityContext private val context: Context
): NaverUseCase {
    override fun getAccessToken() {

        val oauthLoginCallback = object : OAuthLoginCallback {
            override fun onSuccess() {
                // 네이버 로그인 인증이 성공했을 때 수행할 코드 추가

//                Logger.d("AccessToken ${NaverIdLoginSDK.getAccessToken()}")
//                Logger.d("RefreshToken ${NaverIdLoginSDK.getRefreshToken()}")
//                Logger.d("Expires ${NaverIdLoginSDK.getExpiresAt()}")
//                Logger.d("TokenType ${NaverIdLoginSDK.getTokenType()}")
            }
            override fun onFailure(httpStatus: Int, message: String) {
                val errorCode = NaverIdLoginSDK.getLastErrorCode().code
                val errorDescription = NaverIdLoginSDK.getLastErrorDescription()
                Logger.e("errorCode:$errorCode, errorDesc:$errorDescription")
            }
            override fun onError(errorCode: Int, message: String) {
                onFailure(errorCode, message)
            }
        }

        NaverIdLoginSDK.authenticate(context, oauthLoginCallback)
    }

    override fun signOut() {
        NaverIdLoginSDK.logout()
    }
}