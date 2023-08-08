package com.ddd.ansayo.domain.handler.favorite

import com.ddd.ansayo.core_model.common.Response
import com.ddd.ansayo.domain.model.favorite.FavoriteCourseAction
import com.ddd.ansayo.domain.model.favorite.FavoriteCourseMutation
import com.ddd.ansayo.domain.model.favorite.FavoriteCourseState
import com.ddd.ansayo.domain.usecase.favorite.GetFavoriteCoursesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FavoriteCourseMutationHandler @Inject constructor(
    private val getFavoriteCoursesUseCase: GetFavoriteCoursesUseCase
) {

    suspend fun mutate(
        state: FavoriteCourseState,
        action: FavoriteCourseAction
    ) : Flow<FavoriteCourseMutation> {
        return flow {
            when (action) {
                is FavoriteCourseAction.ClickCourse -> {
                    emit(FavoriteCourseMutation.SideEffect.NaviToCourseDetail(action.id))
                }
                is FavoriteCourseAction.ClickFavorite -> {
                    // TODO()
                    val courses = state.courses.map {
                        if (it.todo.toString() == action.id) it
                        else it
                    }
                    emit(FavoriteCourseMutation.Mutation.UpdateFavorite(courses))
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
                            emit(FavoriteCourseMutation.Mutation.UpdateCourses(result.data))
                        }
                    }
                }
            }
        }
    }
}
