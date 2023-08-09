package com.ddd.ansayo.presentation.viewmodel.favorite

import androidx.lifecycle.ViewModel
import com.ddd.ansayo.domain.handler.favorite.FavoriteCourseMutationHandler
import com.ddd.ansayo.domain.model.favorite.FavoriteCourseAction
import com.ddd.ansayo.domain.model.favorite.FavoriteCourseMutation
import com.ddd.ansayo.domain.model.favorite.FavoriteCourseState
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class FavoriteCourseViewModel @Inject constructor(
    private val mutationHandler: FavoriteCourseMutationHandler
) : ContainerHost<FavoriteCourseState, FavoriteCourseMutation.SideEffect>, ViewModel() {

    override val container: Container<FavoriteCourseState, FavoriteCourseMutation.SideEffect> =
        container(FavoriteCourseState.EMPTY)

    fun onAction(action: FavoriteCourseAction) = intent {
        mutationHandler.mutate(state, action)
            .collect { mutation ->
                when (mutation) {
                    is FavoriteCourseMutation.Mutation -> reduce(mutation)
                    is FavoriteCourseMutation.SideEffect -> postSideEffect(mutation)
                }
            }
    }

    private fun reduce(mutation: FavoriteCourseMutation.Mutation) {
        intent {
            reduce {
                when (mutation) {
                    is FavoriteCourseMutation.Mutation.UpdateCourses -> {
                        state.copy(
                            hasFavorites = mutation.hasFavorites,
                            courses = mutation.courses
                        )
                    }

                    is FavoriteCourseMutation.Mutation.UpdateFavorite -> {
                        state.copy(courses = mutation.courses)
                    }
                }
            }
        }
    }
}
