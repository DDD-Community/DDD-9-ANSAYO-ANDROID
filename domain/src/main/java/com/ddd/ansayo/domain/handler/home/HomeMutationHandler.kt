package com.ddd.ansayo.domain.handler.home

import com.ddd.ansayo.domain.model.favorite.FavoritePlaceMutation
import com.ddd.ansayo.domain.model.home.HomeAction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import com.ddd.ansayo.domain.model.home.HomeState
import com.ddd.ansayo.domain.usecase.badge.GetBadgeUseCase
import com.ddd.ansayo.domain.usecase.course.GetPopularCourseUseCase
import com.ddd.ansayo.domain.usecase.course.GetRecentCourseUseCase

import javax.inject.Inject

class HomeMutationHandler @Inject constructor(
    private val getBadgeUseCase: GetBadgeUseCase,
    private val popularCourseUseCase: GetPopularCourseUseCase,
    private val recentCourseUseCase: GetRecentCourseUseCase
) {
    suspend fun mutate(
        state: HomeState,
        action: HomeAction
    ): Flow<FavoritePlaceMutation> {
        return flow {
            when(action) {
                is HomeAction.ClickBadgeCategory -> {

                }
                is HomeAction.ClickPopularCourse -> {

                }
                is HomeAction.ClickBadgeCourse -> {

                }
                is HomeAction.ClickRecordButton -> {

                }
                is HomeAction.ClickSearchButton -> {

                }
            }
        }
    }

}