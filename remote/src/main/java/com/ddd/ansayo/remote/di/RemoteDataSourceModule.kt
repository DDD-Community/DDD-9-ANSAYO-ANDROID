package com.ddd.ansayo.remote.di

import com.ddd.ansayo.data.datasource.account.AccountRemoteDataSource
import com.ddd.ansayo.data.datasource.course.CourseRemoteDataSource
import com.ddd.ansayo.remote.datasource.AccountRemoteDataSourceImpl
import com.ddd.ansayo.remote.datasource.CourseRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDataSourceModule {

    @Binds
    @Singleton
    abstract fun bindsCourseRemoteDataSource(
        courseRemoteDataSourceImpl: CourseRemoteDataSourceImpl
    ): CourseRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindsAccountRemoteDataSource(
        accountRemoteDataSourceImpl: AccountRemoteDataSourceImpl
    ): AccountRemoteDataSource
}
