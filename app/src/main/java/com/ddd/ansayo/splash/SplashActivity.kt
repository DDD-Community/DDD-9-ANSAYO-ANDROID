package com.ddd.ansayo.splash

import android.content.Intent
import android.os.Bundle
import com.ddd.ansayo.base.BaseActivity
import com.ddd.ansayo.databinding.ActivitySplashBinding
import com.ddd.ansayo.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@AndroidEntryPoint
class SplashActivity :
    BaseActivity<ActivitySplashBinding>(ActivitySplashBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
    }

    private fun initView() {
        GlobalScope.launch {
            delay(3000)
            startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
            finish()
        }
    }
}