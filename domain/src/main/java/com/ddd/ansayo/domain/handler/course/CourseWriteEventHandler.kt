package com.ddd.ansayo.domain.handler.course

import com.ddd.ansayo.domain.model.common.Response
import com.ddd.ansayo.domain.model.course.CoursePlaceImage
import com.ddd.ansayo.domain.model.course.CourseWriteEvent
import com.ddd.ansayo.domain.model.course.CourseWriteSideEffect
import com.ddd.ansayo.domain.model.course.CourseWriteState
import javax.inject.Inject

class CourseWriteEventHandler @Inject constructor() {

    fun reduceState(
        currentState: CourseWriteState,
        event: CourseWriteEvent
    ): CourseWriteState {
        return when (event) {
            is CourseWriteEvent.InputCourseTitle -> {
                currentState.copy(isCourseTitleMaxInputted = event.text.length == COURSE_TITLE_MAX_LENGTH)
            }
            is CourseWriteEvent.InputCourseDescription -> {
                currentState.copy(isCourseDescriptionMaxInputted = event.text.length == COURSE_DESCRIPTION_MAX_LENGTH)
            }
            is CourseWriteEvent.SelectDate -> {
                currentState.copy(date = event.date)
            }
            is CourseWriteEvent.SelectImages -> {
                val newPlaces = currentState.places.map { place ->
                    if (place.order == event.placeOrder) {
                        val newImages = (place.images + event.images.map { CoursePlaceImage(origin = it) }).take(PLACE_IMAGE_MAX_COUNT)
                        place.copy(images = newImages)
                    } else {
                        place
                    }
                }
                currentState.copy(places = newPlaces)
            }
            is CourseWriteEvent.ClickDeletePlace -> {
                val newPlaces = currentState.places.filterNot { place -> place.order == event.placeOrder }
                currentState.copy(places = newPlaces)
            }
            is CourseWriteEvent.ClickDeletePlaceImage -> {
                val newPlaces = currentState.places.map { place ->
                    if (place.order == event.placeOrder) {
                        val newImages = place.images.filterIndexed { index, _ -> index != event.imageIndex }
                        place.copy(images = newImages)
                    } else {
                        place
                    }
                }
                currentState.copy(places = newPlaces)
            }
            is CourseWriteEvent.InputPlaceReview -> {
                val newPlaces = currentState.places.map { place ->
                    if (place.order == event.placeOrder) {
                        place.copy(isPlaceReviewMaxInputted = event.text.length == PLACE_REVIEW_MAX_LENGTH)
                    } else {
                        place
                    }
                }
                currentState.copy(places = newPlaces)
            }
            is CourseWriteEvent.ToggleVisibilitySwitch -> {
                currentState.copy(isPrivate = event.checked)
            }
            else -> {
                currentState
            }
        }
    }

    fun handleSideEffect(
        currentState: CourseWriteState,
        event: CourseWriteEvent
    ): CourseWriteSideEffect {
        return when (event) {
            CourseWriteEvent.ClickDatePicker -> {
                CourseWriteSideEffect.ShowDatePickerDialog
            }
            CourseWriteEvent.ClickAddPlace -> {
                if (currentState.places.size == COURSE_PLACE_MAX_COUNT) {
                    CourseWriteSideEffect.ShowSnackBar("TODO")
                } else {
                    CourseWriteSideEffect.None
                }
            }
            is CourseWriteEvent.ClickAddPlaceImage -> {
                val currentImagesCount = currentState.places.first { it.order == event.placeOrder }.images.size
                if (currentImagesCount == PLACE_IMAGE_MAX_COUNT) {
                    CourseWriteSideEffect.ShowSnackBar("TODO")
                } else {
                    CourseWriteSideEffect.None
                }
            }
            CourseWriteEvent.StartApiCall -> {
                CourseWriteSideEffect.ShowLoading
            }
            CourseWriteEvent.CompleteApiCall -> {
                CourseWriteSideEffect.HideLoading
            }
            is CourseWriteEvent.UploadCourse -> {
                when (event.response) {
                    is Response.Fail -> CourseWriteSideEffect.ShowSnackBar(event.response.message)
                    is Response.Success -> CourseWriteSideEffect.Finish
                }
            }
            else -> CourseWriteSideEffect.None
        }
    }

    companion object {
        private const val COURSE_PLACE_MAX_COUNT = 8
        private const val COURSE_TITLE_MAX_LENGTH = 40
        private const val COURSE_DESCRIPTION_MAX_LENGTH = 300
        private const val PLACE_REVIEW_MAX_LENGTH = 300
        private const val PLACE_IMAGE_MAX_COUNT = 4
    }
}
