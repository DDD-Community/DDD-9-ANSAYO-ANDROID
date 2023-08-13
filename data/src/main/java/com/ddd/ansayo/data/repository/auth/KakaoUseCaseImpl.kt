package com.ddd.ansayo.data.repository.auth

import android.content.Context
import com.ddd.ansayo.domain.usecase.login.KakaoUseCase
import com.kakao.sdk.user.UserApiClient
import com.orhanobut.logger.Logger
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class KakaoUseCaseImpl @Inject constructor(
    @ActivityContext private val context: Context
) : KakaoUseCase {
    override fun getAccessToken() {
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
            UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
                if (error != null) {
                    Logger.e("카카오톡으로 로그인 실패 $error")
                } else if (token != null) {
                    Logger.d("카카오톡으로 로그인 성공  ${token.accessToken}")

                } else {
                    Logger.e("유저동작 에러")

                    return@loginWithKakaoTalk
                }
            }

        }else {
            UserApiClient.instance.loginWithKakaoAccount(context) { token, error ->
                if (error != null) {
                    Logger.e(" 로그인 실패 $error")
                } else if (token != null) {
                    Logger.d("로그인 성공  ${token.accessToken}")
                } else {
                    Logger.e("유저동작 에러")

                    return@loginWithKakaoAccount
                }
            }
        }
    }

    override fun signOut() {
        UserApiClient.instance.logout { error ->
            if (error != null) {
                Logger.e( "로그아웃 실패. SDK에서 토큰 삭제됨", error)
            }
            else {
                Logger.d("로그아웃 성공. SDK에서 토큰 삭제됨")
            }
        }

    }
}