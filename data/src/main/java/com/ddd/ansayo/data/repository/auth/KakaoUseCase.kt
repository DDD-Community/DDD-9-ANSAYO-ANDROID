package com.ddd.ansayo.data.repository.auth

import android.content.Context
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import com.orhanobut.logger.Logger

class KakaoUseCase(
    private val context: Context,
    private val authCallback: (OAuthToken?, Throwable?) -> Unit
) {
    fun getAccessToken() {
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
            UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
                if (error != null) {
                    Logger.d("카카오톡으로 로그인 실패 $error")

                    // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
                    // 의도적인 로그인 취소로 보고 카카오계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
                    if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                        return@loginWithKakaoTalk
                    }

                    // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
                    UserApiClient.instance.loginWithKakaoAccount(context, callback = authCallback)
                } else if (token != null) {
                    Logger.d("카카오톡으로 로그인 성공  ${token.accessToken}")
                    authCallback.invoke(token, null)
                }
            }
        } else {
            UserApiClient.instance.loginWithKakaoAccount(context) { token, error ->
                if (error != null) {
                    Logger.d("카카오톡으로 로그인 실패 $error")

                } else if (token != null) {
                    Logger.d("카카오톡으로 로그인 성공  ${token.accessToken}")
                    authCallback.invoke(token, null)
                } else {
                    Logger.d("카카오톡으로 로그인 실패 $error")

                }
            }

            }
        }
        fun signOut() {
            UserApiClient.instance.logout { error ->
                if (error != null) {
                    Logger.e("로그아웃 실패. SDK에서 토큰 삭제됨", error)
                } else {
                    Logger.d("로그아웃 성공. SDK에서 토큰 삭제됨")
                }
            }

        }
    }