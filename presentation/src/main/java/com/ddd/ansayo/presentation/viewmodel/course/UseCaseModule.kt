package com.ddd.ansayo.presentation.viewmodel.course

import com.ddd.ansayo.data.repository.account.KakaoUseCaseImpl
import com.example.domain.account.KakaoUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {
    @Binds
    abstract fun bindKakaoUseCase(kakaoUserCaseImpl: KakaoUseCaseImpl): KakaoUseCase
}