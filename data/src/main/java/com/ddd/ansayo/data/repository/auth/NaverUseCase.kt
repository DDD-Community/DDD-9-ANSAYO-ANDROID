package com.ddd.ansayo.data.repository.auth

import android.content.Context
import com.navercorp.nid.NaverIdLoginSDK
import com.navercorp.nid.oauth.OAuthLoginCallback
import com.orhanobut.logger.Logger
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class NaverUseCase @Inject constructor(
    private val context: Context,
    private val authCallback: (String?, Throwable?) -> Unit
){
    fun getAccessToken() {
        val oauthLoginCallback = object : OAuthLoginCallback {
            override fun onSuccess() {
                // 네이버 로그인 인증이 성공했을 때 수행할 코드 추가
                Logger.d("NaverLogin onSuccess : ${NaverIdLoginSDK.getAccessToken()}")
                authCallback.invoke(NaverIdLoginSDK.getAccessToken(), null)
            }
            override fun onFailure(httpStatus: Int, message: String) {
                val errorCode = NaverIdLoginSDK.getLastErrorCode().code
                val errorDesc = NaverIdLoginSDK.getLastErrorDescription()
                Logger.d("NaverLogin onFailure :errorCode:$errorCode, errorDesc:$errorDesc")

                authCallback.invoke(null, Throwable("errorCode:$errorCode, errorDesc:$errorDesc"))
            }
            override fun onError(errorCode: Int, message: String) {
                onFailure(errorCode, message)
                Logger.e("${errorCode}, ${message}")
            }
        }
        NaverIdLoginSDK.authenticate(context, oauthLoginCallback)
    }

    fun signOut() {
        NaverIdLoginSDK.logout()
    }
}