package com.ddd.ansayo.login

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.ddd.ansayo.base.BaseActivity
import com.ddd.ansayo.data.repository.auth.KakaoUseCase
import com.ddd.ansayo.data.repository.auth.NaverUseCase
import com.ddd.ansayo.databinding.ActivityLoginBinding
import com.ddd.ansayo.domain.model.login.LoginAction
import com.ddd.ansayo.domain.model.login.LoginMutation
import com.ddd.ansayo.local.preference.PreferenceUtil
import com.ddd.ansayo.presentation.viewmodel.login.LoginViewModel
import com.google.android.material.snackbar.Snackbar
import com.orhanobut.logger.Logger
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginActivity:
    BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate) {

    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        collectState()
        collectSideEffect()
    }

    private fun initView() {
        val kakaoLogin = KakaoUseCase(this)  { oAuthToken, throwable ->
            viewModel.onAction(LoginAction.ClickKakaoLogin(oAuthToken!!.accessToken))
            PreferenceUtil(this@LoginActivity).setAuthToken("authToken", oAuthToken.accessToken)
            Logger.d( " kakao token: ${oAuthToken?.accessToken.toString()} or fail : $throwable")
        }
        val naverLogin = NaverUseCase(this) { token, throwable ->
            viewModel.onAction(LoginAction.ClickNaverLogin(token!!))
            PreferenceUtil(this@LoginActivity).setAuthToken("authToken", token)
            Logger.d( "naver token: $token or fail : $throwable")


        }
        binding.run {
            btnLoginKakao.setOnClickListener {
               kakaoLogin.getAccessToken()
            }
            binding.btnLoginNaver.setOnClickListener {
                 naverLogin.getAccessToken()
            }
        }
    }

    private fun collectState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {


                }
            }
        }
    }

    private fun collectSideEffect() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.container.sideEffectFlow.collect{
                    when(it) {
                        is LoginMutation.SideEffect.ShowSnackBar -> {
                            Snackbar
                                .make(binding.root, it.message , Snackbar.LENGTH_SHORT)
                                .show()
                        }
                        is LoginMutation.SideEffect.StartHomeSreen -> {
                            Logger.d("Login Post Auth !")

                        }

                    }

                }
            }
        }
    }

}