package com.ddd.ansayo.data.di

import com.ddd.ansayo.data.repository.auth.KakaoUseCaseImpl
import com.ddd.ansayo.data.repository.auth.NaverUseCaseImpl
import com.ddd.ansayo.domain.usecase.login.KakaoUseCase
import com.ddd.ansayo.domain.usecase.login.NaverUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {
    @Binds
    abstract fun bindKakaoUseCase(kakaoUserCaseImpl: KakaoUseCaseImpl): KakaoUseCase

    @Binds
    abstract fun bindNaverUseCase(naverUseCaseImpl: NaverUseCaseImpl): NaverUseCase

//    @Binds
//    abstract fun bindSocialLoginUseCase(socialLoginUseCaseImpl: SocialLoginUseCaseImpl): SocialLoginUseCase
}