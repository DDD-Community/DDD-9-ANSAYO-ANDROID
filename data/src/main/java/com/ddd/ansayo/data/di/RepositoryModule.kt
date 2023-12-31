package com.ddd.ansayo.data.di

import com.ddd.ansayo.data.repository.SearchRepositoryImpl
import com.ddd.ansayo.data.repository.auth.AuthRepositoryImpl
import com.ddd.ansayo.data.repository.course.CourseRepositoryImpl
import com.ddd.ansayo.data.repository.place.PlaceRepositoryImpl
import com.ddd.ansayo.domain.repository.AuthRepository
import com.ddd.ansayo.domain.repository.CourseRepository
import com.ddd.ansayo.domain.repository.PlaceRepository
import com.ddd.ansayo.domain.repository.SearchRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindsCourseRepository(
        courseRepositoryImpl: CourseRepositoryImpl
    ): CourseRepository

    @Binds
    @Singleton
    abstract fun bindsPlaceRepository(
        placeRepositoryImpl: PlaceRepositoryImpl
    ): PlaceRepository

    @Binds
    @Singleton
    abstract fun bindsSearchRepository(
        searchRepositoryImpl: SearchRepositoryImpl
    ): SearchRepository

    @Binds
    @Singleton
    abstract fun bindsAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository
}
