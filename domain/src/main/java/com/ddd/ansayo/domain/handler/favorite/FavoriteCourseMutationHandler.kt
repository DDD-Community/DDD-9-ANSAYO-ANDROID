package com.ddd.ansayo.domain.handler.favorite

import com.ddd.ansayo.core_model.common.Response
import com.ddd.ansayo.domain.model.favorite.FavoriteCourseAction
import com.ddd.ansayo.domain.model.favorite.FavoriteCourseMutation
import com.ddd.ansayo.domain.model.favorite.FavoriteCourseState
import com.ddd.ansayo.domain.usecase.favorite.DeleteFavoriteCourseUseCase
import com.ddd.ansayo.domain.usecase.favorite.GetFavoriteCoursesUseCase
import com.ddd.ansayo.domain.usecase.favorite.PostFavoriteCourseUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FavoriteCourseMutationHandler @Inject constructor(
    private val getFavoriteCoursesUseCase: GetFavoriteCoursesUseCase,
    private val postFavoriteCourseUseCase: PostFavoriteCourseUseCase,
    private val deleteFavoriteCourseUseCase: DeleteFavoriteCourseUseCase
) {

    suspend fun mutate(
        state: FavoriteCourseState,
        action: FavoriteCourseAction
    ): Flow<FavoriteCourseMutation> {
        return flow {
            when (action) {
                is FavoriteCourseAction.ClickCourse -> {
                    emit(FavoriteCourseMutation.SideEffect.NaviToCourseDetail(action.id))
                }

                is FavoriteCourseAction.ClickFavorite -> {
                    when (val result =
                        if (action.like) {
                            deleteFavoriteCourseUseCase(action.id)
                        } else {
                            postFavoriteCourseUseCase(action.id)
                        }
                    ) {
                        is Response.Success -> {
                            val courses = state.courses.map {
                                if (it.id == action.id) it.copy(isFavorite = action.like.not())
                                else it
                            }
                            emit(FavoriteCourseMutation.Mutation.UpdateFavorite(courses))
                        }

                        is Response.Fail -> {
                            emit(FavoriteCourseMutation.SideEffect.ShowSnackBar(result.message))
                        }
                    }
                }

                FavoriteCourseAction.ClickFindCourse -> {
                    emit(FavoriteCourseMutation.SideEffect.NaviToSearch)
                }

                FavoriteCourseAction.SelectFavoriteListTab -> {
                    when (val result = getFavoriteCoursesUseCase()) {
                        is Response.Fail -> {
                            emit(FavoriteCourseMutation.SideEffect.ShowSnackBar(result.message))
                        }

                        is Response.Success -> {
                            emit(
                                FavoriteCourseMutation.Mutation.UpdateCourses(
                                    courses = result.data.courses.orEmpty(),
                                    hasFavorites = result.data.courses.isNullOrEmpty().not()
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}
