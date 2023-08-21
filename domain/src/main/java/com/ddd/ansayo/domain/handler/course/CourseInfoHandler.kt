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
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class CourseInfoHandler @Inject constructor(
    private val getCourseInfoUseCase: GetCourseInfoUseCase,
    private val postFavoritePlaceUseCase: PostFavoritePlaceUseCase,
    private val deleteFavoritePlaceUseCase: DeleteFavoritePlaceUseCase
) {
    suspend fun mutate(
        state: CourseInfoState,
        action: CourseInfoAction
    ) : Flow<CourseInfoMutation> {
        return flow {
            when (action) {
                is CourseInfoAction.BackScreen -> {
                    flowOf(CourseInfoMutation.SideEffect.BackScreen)
                }
                is CourseInfoAction.NaviToPlaceDetail -> {
                    emit(CourseInfoMutation.SideEffect.NaviToPlaceDetail(action.id))
                }
                is CourseInfoAction.ClickFavorite -> {
                    when ( val result =
                        if (action.like) {
                            deleteFavoritePlaceUseCase(action.id)
                        } else {
                            postFavoritePlaceUseCase(action.id)
                        }
                    ) {
                        is Response.Success -> {
                            val places = state.courseInfo.places.map {
                                if (it.placeId == action.id) it.copy(isFavorite = action.like.not())
                                else it
                            }
                            emit(CourseInfoMutation.Mutation.UpdateFavorite(places))
                        }

                        is Response.Fail -> {
                        }
                    }
                }
                is CourseInfoAction.SelectCourseInfo -> {
                    when (val result = getCourseInfoUseCase(action.courseId)) {
                        is Response.Success -> {
                            emit(CourseInfoMutation.Mutation.UpdateCourseInfo(
                                courseInfo = result.data
                            ))
                        }
                        is Response.Fail -> {

                        }

                    }
                }

            }
        }
    }
}