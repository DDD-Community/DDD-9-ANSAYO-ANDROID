package com.ddd.ansayo.main.home

import androidx.lifecycle.ViewModel
import com.ddd.ansayo.domain.handler.home.HomeMutationHandler
import com.ddd.ansayo.domain.model.home.HomeAction
import com.ddd.ansayo.domain.model.home.HomeMutation
import com.ddd.ansayo.domain.model.home.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val mutationHandler: HomeMutationHandler
) : ContainerHost<HomeState, HomeMutation.SideEffect>, ViewModel() {

    override val container: Container<HomeState, HomeMutation.SideEffect> =
        container(HomeState.EMPTY)

    fun onAction(action: HomeAction) = intent {
        mutationHandler.mutate(state, action)
            .collect { mutation ->
                when (mutation) {
                    is HomeMutation.Mutation -> reduce(mutation)
                    is HomeMutation.SideEffect -> postSideEffect(mutation)
                }
            }
    }
    private fun reduce(mutation: HomeMutation.Mutation) {
        intent {
            reduce {
                when (mutation) {
                    is HomeMutation.Mutation.GetSearchableBadge -> {
                        state.copy(badge = mutation.badge)
                    }
                    is HomeMutation.Mutation.GetPopularCourseBadge -> {
                        state.copy(badgeCourse = mutation.course)
                    }
                    is HomeMutation.Mutation.GetRecommandCourseBadge -> {
                        state.copy(recommandCourse = mutation.course)
                    }
                }
            }
        }
    }

}