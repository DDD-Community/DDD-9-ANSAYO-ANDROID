package com.ddd.ansayo.presentation.viewmodel.course

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ddd.ansayo.domain.handler.course.CourseWriteEventHandler
import com.ddd.ansayo.domain.model.course.CourseWriteEvent
import com.ddd.ansayo.domain.model.course.CourseWriteSideEffect
import com.ddd.ansayo.domain.model.course.CourseWriteState
import com.ddd.ansayo.domain.usecase.course.GetImageUploadUrlUseCase
import com.ddd.ansayo.presentation.model.course.CourseWriteAction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class CourseCreateViewModel @Inject constructor(
    private val eventHandler: CourseWriteEventHandler,
    private val getImageUploadUrlUseCase: GetImageUploadUrlUseCase
) : ContainerHost<CourseWriteState, CourseWriteSideEffect>, ViewModel() {

    override val container: Container<CourseWriteState, CourseWriteSideEffect> =
        container(CourseWriteState.EMPTY)

    fun onAction(action: CourseWriteAction) {
        viewModelScope.launch {
            when (action) {
                CourseWriteAction.ClickAddPlace -> {
                    onEvent(CourseWriteEvent.ClickAddPlace)
                }

                is CourseWriteAction.ClickAddPlaceImage -> {
                    onEvent(CourseWriteEvent.ClickAddPlaceImage(action.placeOrder))
                }

                CourseWriteAction.ClickDatePicker -> {
                    onEvent(CourseWriteEvent.ClickDatePicker)
                }

                is CourseWriteAction.ClickDeletePlace -> {
                    onEvent(CourseWriteEvent.ClickDeletePlace(action.placeOrder))
                }

                is CourseWriteAction.ClickDeletePlaceImage -> {
                    onEvent(
                        CourseWriteEvent.ClickDeletePlaceImage(
                            placeOrder = action.placeOrder,
                            imageIndex = action.imageIndex
                        )
                    )
                }

                is CourseWriteAction.ClickUploadButton -> {
                    // TODO("")
                    getImageUploadUrlUseCase("", "")
                        .onStart { onEvent(CourseWriteEvent.StartApiCall) }
                        .onCompletion { onEvent(CourseWriteEvent.CompleteApiCall) }
                        .collect { onEvent(CourseWriteEvent.UploadCourse(it)) }
                }

                is CourseWriteAction.InputCourseDescription -> {
                    onEvent(CourseWriteEvent.InputCourseDescription(action.text))
                }

                is CourseWriteAction.InputCourseTitle -> {
                    onEvent(CourseWriteEvent.InputCourseTitle(action.text))
                }

                is CourseWriteAction.InputPlaceReview -> {
                    onEvent(
                        CourseWriteEvent.InputPlaceReview(
                            placeOrder = action.placeOrder,
                            text = action.text
                        )
                    )
                }

                is CourseWriteAction.SelectDate -> {
                    onEvent(CourseWriteEvent.SelectDate(action.date))
                }

                is CourseWriteAction.SelectImages -> {
                    onEvent(
                        CourseWriteEvent.SelectImages(
                            placeOrder = action.placeOrder,
                            images = action.images
                        )
                    )
                }

                is CourseWriteAction.ToggleVisibilitySwitch -> {
                    onEvent(CourseWriteEvent.ToggleVisibilitySwitch(action.checked))
                }
            }
        }
    }

    private fun onEvent(event: CourseWriteEvent) {
        intent {
            val currentState = container.stateFlow.value
            reduce { eventHandler.reduceState(currentState, event) }
            postSideEffect(eventHandler.handleSideEffect(currentState, event))
        }
    }
}
