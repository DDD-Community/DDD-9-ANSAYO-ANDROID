package com.ddd.ansayo.domain.handler.course

import com.ddd.ansayo.core_model.common.Response
import com.ddd.ansayo.domain.model.course.info.CourseInfoAction
import com.ddd.ansayo.domain.model.course.info.CourseInfoMutation
import com.ddd.ansayo.domain.model.course.info.CourseInfoState
import com.ddd.ansayo.domain.usecase.course.GetCourseInfoUseCase
import com.ddd.ansayo.domain.usecase.favorite.DeleteFavoritePlaceUseCase
import com.ddd.ansayo.domain.usecase.favorite.PostFavoritePlaceUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CourseInfoMutationHandler @Inject constructor(
    private val getCourseInfoUseCase: GetCourseInfoUseCase,
    private val postFavoritePlaceUseCase: PostFavoritePlaceUseCase,
    private val deleteFavoritePlaceUseCase: DeleteFavoritePlaceUseCase
) {
    suspend fun mutate(
        state: CourseInfoState,
        action: CourseInfoAction
    ): Flow<CourseInfoMutation> {
        return flow {
            when (action) {
                is CourseInfoAction.BackScreen -> {
                    emit(CourseInfoMutation.SideEffect.BackScreen)
                }

                is CourseInfoAction.ClickPlace -> {
                    emit(CourseInfoMutation.SideEffect.NaviToPlaceDetail(action.id))
                }

                is CourseInfoAction.ClickFavorite -> {
                    when (val result =
                        if (action.like) {
                            deleteFavoritePlaceUseCase(state.courseInfo.course.id)
                        } else {
                            postFavoritePlaceUseCase(state.courseInfo.course.id)
                        }
                    ) {
                        is Response.Success -> {
                            val favoriteCount = if (action.like) {
                                state.courseInfo.course.favorites - 1
                            } else {
                                state.courseInfo.course.favorites + 1
                            }
                            emit(
                                CourseInfoMutation.Mutation.UpdateFavoriteCount(
                                    state.courseInfo.course.copy(
                                        favorites = favoriteCount,
                                        isFavorite = state.courseInfo.course.isFavorite.not()
                                    )
                                )
                            )
                        }

                        is Response.Fail -> {
                            emit(CourseInfoMutation.SideEffect.ShowSnackBar(result.message))
                        }
                    }
                }

                is CourseInfoAction.EnteredScreen -> {
                    when (val result = getCourseInfoUseCase(action.courseId)) {
                        is Response.Success -> {
                            emit(
                                CourseInfoMutation.Mutation.UpdateCourseInfo(
                                    courseInfo = result.data
                                )
                            )
                        }

                        is Response.Fail -> {
                            emit(CourseInfoMutation.SideEffect.ShowSnackBar(result.message))
                        }

                    }
                }

            }
        }
    }
}