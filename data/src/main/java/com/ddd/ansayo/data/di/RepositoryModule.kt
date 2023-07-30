package com.ddd.ansayo.data.di

import com.ddd.ansayo.data.repository.course.CourseRepositoryImpl
import com.ddd.ansayo.domain.repository.CourseRepository
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
}
