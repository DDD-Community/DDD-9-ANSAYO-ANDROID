package com.ddd.ansayo.presentation.viewmodel.course

import androidx.lifecycle.ViewModel
import com.ddd.ansayo.domain.handler.course.CourseWriteMutationHandler
import com.ddd.ansayo.domain.model.course.CourseWriteAction
import com.ddd.ansayo.domain.model.course.CourseWriteMutation
import com.ddd.ansayo.domain.model.course.CourseWriteState
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class CourseCreateViewModel @Inject constructor(
    private val mutationHandler: CourseWriteMutationHandler
) : ContainerHost<CourseWriteState, CourseWriteMutation.SideEffect>, ViewModel() {

    override val container: Container<CourseWriteState, CourseWriteMutation.SideEffect> =
        container(CourseWriteState.EMPTY)

    fun onAction(action: CourseWriteAction) = intent {
        mutationHandler.mutate(state, action)
            .collect { mutation ->
                when (mutation) {
                    is CourseWriteMutation.Mutation -> reduce(mutation)
                    is CourseWriteMutation.SideEffect -> postSideEffect(mutation)
                }
            }
    }

    private fun reduce(mutation: CourseWriteMutation.Mutation) {
        intent {
            reduce {
                when (mutation) {
                    is CourseWriteMutation.Mutation.AddPlaceImages -> {
                        state.copy(places = mutation.places)
                    }

                    is CourseWriteMutation.Mutation.DeletePlace -> {
                        state.copy(places = mutation.places)
                    }

                    is CourseWriteMutation.Mutation.DeletePlaceImage -> {
                        state.copy(places = mutation.places)
                    }

                    is CourseWriteMutation.Mutation.UpdateCourseDate -> {
                        state.copy(
                            header = state.header.copy(date = mutation.date)
                        )
                    }

                    is CourseWriteMutation.Mutation.UpdateCourseDescription -> {
                        state.copy(
                            header = state.header.copy(
                                description = mutation.description,
                                isCourseDescriptionMaxInputted = mutation.isCourseDescriptionMaxInputted
                            )
                        )
                    }

                    is CourseWriteMutation.Mutation.UpdateCourseTitle -> {
                        state.copy(
                            header = state.header.copy(
                                title = mutation.title,
                                isCourseTitleMaxInputted = mutation.isCourseTitleMaxInputted
                            )
                        )
                    }

                    is CourseWriteMutation.Mutation.UpdateCourseVisibility -> {
                        state.copy(
                            footer = state.footer.copy(isPrivate = mutation.isPrivate)
                        )
                    }

                    is CourseWriteMutation.Mutation.UpdatePlaceReview -> {
                        state.copy(places = mutation.places)
                    }

                    is CourseWriteMutation.Mutation.AddPlace -> {
                        state.copy(places = mutation.places)
                    }

                    is CourseWriteMutation.Mutation.UpdateCompleteButton -> {
                        state.copy(
                            footer = state.footer.copy(
                                isConfirmButtonEnabled = mutation.isConfirmButtonEnabled
                            )
                        )
                    }
                }
            }
        }
    }
}
