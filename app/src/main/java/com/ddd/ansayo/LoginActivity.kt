package com.ddd.ansayo

import android.app.Activity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.ddd.ansayo.base.BaseActivity
import com.ddd.ansayo.databinding.ActivityLoginBinding
import com.ddd.ansayo.domain.model.login.LoginAction
import com.ddd.ansayo.domain.model.login.LoginMutation
import com.ddd.ansayo.domain.usecase.login.KakaoUseCase
import com.ddd.ansayo.domain.util.toResponse
import com.ddd.ansayo.presentation.viewmodel.login.LoginViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

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
        binding.run {
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

                    }

                }
            }
        }
    }

}