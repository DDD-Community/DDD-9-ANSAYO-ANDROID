package com.ddd.ansayo.login

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.ddd.ansayo.base.BaseActivity
import com.ddd.ansayo.data.repository.auth.KakaoUseCase
import com.ddd.ansayo.databinding.ActivityLoginBinding
import com.ddd.ansayo.domain.model.login.LoginAction
import com.ddd.ansayo.domain.model.login.LoginMutation
import com.ddd.ansayo.domain.util.toResponse
import com.ddd.ansayo.local.preference.PreferenceUtil
import com.ddd.ansayo.presentation.viewmodel.login.LoginViewModel
import com.google.android.material.snackbar.Snackbar
import com.kakao.sdk.common.util.Utility
import com.orhanobut.logger.Logger
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
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
        var keyHash = Utility.getKeyHash(this)
        Logger.e(keyHash)
    }

    private fun initView() {
        val kakaoLogin = KakaoUseCase(this)  { oAuthToken, throwable ->
            viewModel.onAction(LoginAction.ClickKakaoLogin(oAuthToken!!.accessToken))
            Logger.d( oAuthToken?.accessToken.toString())
            Logger.d( throwable?.message)

        }
        binding.run {
            btnLoginKakao.setOnClickListener {
               kakaoLogin.getAccessToken()
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