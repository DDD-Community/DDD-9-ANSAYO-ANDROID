package com.ddd.ansayo.data.di

import com.ddd.ansayo.data.repository.account.KakaoUseCaseImpl
import com.ddd.ansayo.data.repository.account.NaverUseCaseImpl
import com.example.domain.account.KakaoUseCase
import com.example.domain.account.NaverUseCase
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