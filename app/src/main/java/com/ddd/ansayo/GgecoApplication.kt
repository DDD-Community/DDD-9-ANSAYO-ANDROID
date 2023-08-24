package com.ddd.ansayo

import android.app.Application
import android.content.Context
import com.ddd.ansayo.BuildConfig.DEBUG
import com.ddd.ansayo.BuildConfig.KAKAO_NATIVE_KEY
import com.ddd.ansayo.BuildConfig.NAVER_CLIENT_ID
import com.ddd.ansayo.BuildConfig.NAVER_CLIENT_SECRET
import com.ddd.ansayo.local.preference.PreferenceUtil
import com.kakao.sdk.common.KakaoSdk
import com.navercorp.nid.NaverIdLoginSDK
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GgecoApplication: Application() {

    companion object {
        lateinit var prefs : PreferenceUtil
    }

    override fun onCreate() {
        super.onCreate()

        initLogger()
        initKakaoSdk()
        initNaverSdk()
        initPreference()
    }

    private fun initLogger() {
        Logger.addLogAdapter(object : AndroidLogAdapter() {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return DEBUG
            }
        })
    }
    private fun initKakaoSdk() {
        KakaoSdk.init(this, KAKAO_NATIVE_KEY)
    }

    private fun initNaverSdk() {
        NaverIdLoginSDK.initialize(applicationContext, NAVER_CLIENT_ID, NAVER_CLIENT_SECRET,getString(R.string.app_name))
    }

    private fun initPreference() {
        prefs = PreferenceUtil(applicationContext)
    }
}
