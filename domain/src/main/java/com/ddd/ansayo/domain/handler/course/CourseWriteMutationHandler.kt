package com.ddd.ansayo.domain.handler.course

import com.ddd.ansayo.domain.model.common.Response
import com.ddd.ansayo.domain.model.course.CoursePlaceImage
import com.ddd.ansayo.domain.model.course.CourseWriteMutation
import com.ddd.ansayo.domain.model.course.CourseWriteAction
import com.ddd.ansayo.domain.model.course.CourseWriteState
import com.ddd.ansayo.domain.usecase.course.GetImageUploadUrlUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class CourseWriteMutationHandler @Inject constructor(
    private val getImageUploadUrlUseCase: GetImageUploadUrlUseCase
) {

    suspend fun mutate(
        state: CourseWriteState,
        action: CourseWriteAction
    ): Flow<CourseWriteMutation> {
        return when (action) {
            is CourseWriteAction.InputCourseTitle -> {
                flowOf(
                    CourseWriteMutation.Mutation.UpdateCourseTitle(
                        isCourseTitleMaxInputted = action.text.length == COURSE_TITLE_MAX_LENGTH
                    )
                )
            }

            is CourseWriteAction.InputCourseDescription -> {
                flowOf(
                    CourseWriteMutation.Mutation.UpdateCourseDescription(
                        isCourseDescriptionMaxInputted = action.text.length == COURSE_DESCRIPTION_MAX_LENGTH
                    )
                )
            }

            is CourseWriteAction.SelectDate -> {
                flowOf(
                    CourseWriteMutation.Mutation.UpdateCourseDate(date = action.date)
                )
            }

            is CourseWriteAction.SelectImages -> {
                val newPlaces = state.places.map { place ->
                    if (place.order == action.placeOrder) {
                        val newImages =
                            (place.images + action.images.map { CoursePlaceImage(origin = it) }).take(
                                PLACE_IMAGE_MAX_COUNT
                            )
                        place.copy(images = newImages)
                    } else {
                        place
                    }
                }
                flowOf(
                    CourseWriteMutation.Mutation.AddPlaceImages(places = newPlaces)
                )
            }

            is CourseWriteAction.ClickDeletePlace -> {
                val newPlaces = state.places.filterNot { place -> place.order == action.placeOrder }
                flowOf(
                    CourseWriteMutation.Mutation.DeletePlace(places = newPlaces)
                )
            }

            is CourseWriteAction.ClickDeletePlaceImage -> {
                val newPlaces = state.places.map { place ->
                    if (place.order == action.placeOrder) {
                        val newImages =
                            place.images.filterIndexed { index, _ -> index != action.imageIndex }
                        place.copy(images = newImages)
                    } else {
                        place
                    }
                }
                flowOf(
                    CourseWriteMutation.Mutation.DeletePlaceImage(places = newPlaces)
                )
            }

            is CourseWriteAction.InputPlaceReview -> {
                val newPlaces = state.places.map { place ->
                    if (place.order == action.placeOrder) {
                        place.copy(isPlaceReviewMaxInputted = action.text.length == PLACE_REVIEW_MAX_LENGTH)
                    } else {
                        place
                    }
                }
                flowOf(
                    CourseWriteMutation.Mutation.UpdatePlaceReview(places = newPlaces)
                )
            }

            is CourseWriteAction.ToggleVisibilitySwitch -> {
                flowOf(
                    CourseWriteMutation.Mutation.UpdateCourseVisibility(isPrivate = action.checked)
                )
            }

            CourseWriteAction.ClickDatePicker -> {
                flowOf(CourseWriteMutation.SideEffect.ShowDatePickerDialog)
            }

            CourseWriteAction.ClickAddPlace -> {
                if (state.places.size == COURSE_PLACE_MAX_COUNT) {
                    flowOf(CourseWriteMutation.SideEffect.ShowSnackBar("TODO"))
                } else {
                    flowOf(CourseWriteMutation.SideEffect.GoToAddPlace)
                }
            }

            is CourseWriteAction.ClickAddPlaceImage -> {
                val currentImagesCount =
                    state.places.first { it.order == action.placeOrder }.images.size
                if (currentImagesCount == PLACE_IMAGE_MAX_COUNT) {
                    flowOf(CourseWriteMutation.SideEffect.ShowSnackBar("TODO"))
                } else {
                    val remainCount = PLACE_IMAGE_MAX_COUNT - currentImagesCount
                    flowOf(
                        CourseWriteMutation.SideEffect.ShowPhotoPicker(remainCount = remainCount)
                    )
                }
            }

            is CourseWriteAction.ClickUploadButton -> {
                val result = when (val response = getImageUploadUrlUseCase("", "")) {
                    is Response.Fail -> CourseWriteMutation.SideEffect.ShowSnackBar(response.message)
                    is Response.Success -> CourseWriteMutation.SideEffect.Finish
                }

                flowOf(
                    CourseWriteMutation.SideEffect.ShowLoading,
                    result,
                    CourseWriteMutation.SideEffect.HideLoading,
                )
            }
        }
    }

    companion object {
        private const val COURSE_PLACE_MAX_COUNT = 8
        private const val COURSE_TITLE_MAX_LENGTH = 30
        private const val COURSE_DESCRIPTION_MAX_LENGTH = 300
        private const val PLACE_REVIEW_MAX_LENGTH = 300
        private const val PLACE_IMAGE_MAX_COUNT = 4
    }
}
