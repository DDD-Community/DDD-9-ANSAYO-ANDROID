package com.ddd.ansayo.login

import android.content.Intent
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.ddd.ansayo.R
import com.ddd.ansayo.base.BaseActivity
import com.ddd.ansayo.core_design.util.snackbar.SnackBarLineMax
import com.ddd.ansayo.data.repository.auth.KakaoUseCase
import com.ddd.ansayo.data.repository.auth.NaverUseCase
import com.ddd.ansayo.databinding.ActivityLoginBinding
import com.ddd.ansayo.domain.model.login.LoginAction
import com.ddd.ansayo.domain.model.login.LoginMutation
import com.ddd.ansayo.local.preference.PreferenceUtil
import com.ddd.ansayo.main.MainActivity
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
        collectSideEffect()
    }

    private fun initView() {
//        if (PreferenceUtil(this@LoginActivity).getAuthToken("authToken").isNotEmpty()) {
//            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
//        }
        val kakaoLogin = KakaoUseCase(this)  { oAuthToken, throwable ->
            viewModel.onAction(LoginAction.ClickKakaoLogin(oAuthToken!!.accessToken))
            PreferenceUtil(this@LoginActivity).setAuthToken("authToken", oAuthToken.accessToken)
        }
        val naverLogin = NaverUseCase(this) { token, throwable ->
            viewModel.onAction(LoginAction.ClickNaverLogin(token!!))
            PreferenceUtil(this@LoginActivity).setAuthToken("authToken", token)
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
    private fun collectSideEffect() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.container.sideEffectFlow.collect{
                    when(it) {
                        is LoginMutation.SideEffect.ShowSnackBar -> {
                            SnackBarLineMax(binding.root,it.message).show()
                            Logger.e(it.message)
                        }
                        is LoginMutation.SideEffect.StartHomeScreen -> {
                            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                        }

                    }

                }
            }
        }
    }

}