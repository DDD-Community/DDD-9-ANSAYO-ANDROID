package com.ddd.ansayo.domain.handler.home

import com.ddd.ansayo.core_model.common.Response
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
    private val getPopularCourseUseCase: GetPopularCourseUseCase,
    private val recentCourseUseCase: GetRecommendCourseUseCase
) {
    suspend fun mutate(
        state: HomeState,
        action: HomeAction
    ): Flow<HomeMutation> {
        return flow {
            when(action) {
                is HomeAction.SearchableBadgeList -> {
                    when (val result = getBadgeUseCase()) {
                        is Response.Fail -> {
                            emit(HomeMutation.SideEffect.ShowSnackBar(result.message))
                        }
                        is Response.Success -> {
                            emit(HomeMutation.Mutation.GetSearchableBadge(badge = result.data.badges))
                        }
                    }
                }
                is HomeAction.GetBasicPopularCourse -> {
                    when (val result = getPopularCourseUseCase(badgeId = action.badgeId)) {
                        is Response.Fail -> {
                            emit(HomeMutation.SideEffect.ShowSnackBar(result.message))
                        }
                        is Response.Success -> {
                            emit(HomeMutation.Mutation.GetPopularCourseBadge(course = result.data.courses))
                        }
                    }
                }
                is HomeAction.ClickBadgeCategory -> {
                    when (val result = getPopularCourseUseCase(badgeId = action.badgeId)) {
                        is Response.Fail -> {
                            emit(HomeMutation.SideEffect.ShowSnackBar(result.message))
                        }
                        is Response.Success -> {
                            emit(HomeMutation.Mutation.GetPopularCourseBadge(course = result.data.courses))
                        }
                    }
                }
                is HomeAction.GetRecommandCourseBadge -> {
                    when (val result = recentCourseUseCase()) {
                        is Response.Fail -> {
                            emit(HomeMutation.SideEffect.ShowSnackBar(result.message))
                        }
                        is Response.Success -> {
                            emit(HomeMutation.Mutation.GetRecommandCourseBadge(course = result.data.courses))
                        }
                    }
                }
                is HomeAction.ClickBadgeCourse -> {

                }
                is HomeAction.ClickRecordButton -> {
                    emit(HomeMutation.SideEffect.NaviToRecord)

                }
                is HomeAction.ClickSearchButton -> {
                    emit(HomeMutation.SideEffect.NaviToSearch)
                }
            }
        }
    }

}