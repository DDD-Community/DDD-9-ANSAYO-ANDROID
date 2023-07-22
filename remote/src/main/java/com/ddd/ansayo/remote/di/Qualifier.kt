package com.ddd.ansayo.remote.di

import javax.inject.Qualifier

@Qualifier
@Retention(value = AnnotationRetention.BINARY)
annotation class AuthClient

@Qualifier
@Retention(value = AnnotationRetention.BINARY)
annotation class NoAuthClient

@Qualifier
@Retention(value = AnnotationRetention.BINARY)
annotation class FileUploadClient
