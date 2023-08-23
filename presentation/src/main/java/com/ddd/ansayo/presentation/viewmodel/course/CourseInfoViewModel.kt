package com.ddd.ansayo.presentation.viewmodel.course

import androidx.lifecycle.ViewModel
import com.ddd.ansayo.domain.handler.course.CourseInfoMutationHandler
import com.ddd.ansayo.domain.model.course.info.CourseInfoAction
import com.ddd.ansayo.domain.model.course.info.CourseInfoMutation
import com.ddd.ansayo.domain.model.course.info.CourseInfoState
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class CourseInfoViewModel @Inject constructor(
    private val mutationHandler: CourseInfoMutationHandler
) : ContainerHost<CourseInfoState,CourseInfoMutation.SideEffect>, ViewModel() {

    override val container: Container<CourseInfoState, CourseInfoMutation.SideEffect> =
        container(CourseInfoState.EMPTY)

    fun onAction(action: CourseInfoAction) = intent {
        mutationHandler.mutate(state,action)
            .collect {mutation ->
                when(mutation) {
                    is CourseInfoMutation.Mutation -> reduce(mutation)
                    is CourseInfoMutation.SideEffect -> postSideEffect(mutation)
                }
            }
    }
    private fun reduce(mutation: CourseInfoMutation.Mutation) {
        intent {
            reduce {
                when(mutation) {
                    is CourseInfoMutation.Mutation.UpdateFavorite -> {
                        state.copy(course = mutation.course)
                    }
                    is CourseInfoMutation.Mutation.UpdateCourseInfo -> {
                        state.copy(courseInfo = mutation.courseInfo)
                    }
                }
            }
        }
    }
}