package com.ddd.ansayo.data.di

import com.ddd.ansayo.data.repository.design.DialogUseCaseImpl
import com.ddd.ansayo.data.repository.design.ToastUseCaseImpl
import com.ddd.ansayo.domain.usecase.design.DialogUseCase
import com.ddd.ansayo.domain.usecase.design.ToastUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

/**
 * Activity Context에 의존하는 유틸리티 UseCase Module
 */
@Module
@InstallIn(ActivityComponent::class)
abstract class UtilUseCaseModule {

    @Binds
    abstract fun bindToastUseCase(toastUseCaseImpl: ToastUseCaseImpl): ToastUseCase

    @Binds
    abstract fun bindDialogUseCase(dialogUseCaseImpl: DialogUseCaseImpl): DialogUseCase
}