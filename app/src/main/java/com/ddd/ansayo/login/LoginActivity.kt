package com.ddd.ansayo.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.ddd.ansayo.base.BaseActivity
import com.ddd.ansayo.core_design.util.snackbar.SnackBarLineMax
import com.ddd.ansayo.data.AuthLocalDataSource
import com.ddd.ansayo.data.repository.auth.KakaoUseCase
import com.ddd.ansayo.data.repository.auth.NaverUseCase
import com.ddd.ansayo.databinding.ActivityLoginBinding
import com.ddd.ansayo.domain.model.login.LoginAction
import com.ddd.ansayo.domain.model.login.LoginMutation
import com.ddd.ansayo.main.MainActivity
import com.ddd.ansayo.presentation.viewmodel.login.LoginViewModel
import com.ddd.ansayo.search.SearchActivity
import com.ddd.ansayo.search.SearchListActivity
import com.orhanobut.logger.Logger
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity:
    BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate) {

    private val viewModel by viewModels<LoginViewModel>()

    @Inject
    lateinit var authLocalDataSource: AuthLocalDataSource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        collectSideEffect()
    }

    private fun initView() {
        val isLocalToken = authLocalDataSource.authToken
        if (!isLocalToken.isNullOrEmpty()) {
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
        }
            val kakaoLogin = KakaoUseCase(this)  { oAuthToken, _ ->
                viewModel.onAction(LoginAction.ClickKakaoLogin(oAuthToken!!.accessToken))
            }
            val naverLogin = NaverUseCase(this) { token, _ ->
                viewModel.onAction(LoginAction.ClickNaverLogin(token!!))
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
                            authLocalDataSource.authToken = it.token
                        }

                    }

                }
            }
        }
    }

}