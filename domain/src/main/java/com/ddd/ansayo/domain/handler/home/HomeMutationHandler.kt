package com.ddd.ansayo.domain.handler.home

import com.ddd.ansayo.domain.model.favorite.FavoritePlaceMutation
import com.ddd.ansayo.domain.model.home.HomeAction
import com.ddd.ansayo.domain.model.home.HomeMutation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import com.ddd.ansayo.domain.model.home.HomeState
import com.ddd.ansayo.domain.usecase.badge.GetSearchableBadgeUseCase
import com.ddd.ansayo.domain.usecase.course.GetPopularCourseUseCase
import com.ddd.ansayo.domain.usecase.course.GetRecommendCourseUseCase

import javax.inject.Inject

class HomeMutationHandler @Inject constructor(
    private val getBadgeUseCase: GetSearchableBadgeUseCase,
    private val popularCourseUseCase: GetPopularCourseUseCase,
    private val recentCourseUseCase: GetRecommendCourseUseCase
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
                    emit(HomeMutation.SideEffect.)

                }
                is HomeAction.ClickSearchButton -> {

                }
            }
        }
    }

}