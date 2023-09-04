package com.ddd.ansayo.domain.handler.course

import com.ddd.ansayo.core_model.common.Response
import com.ddd.ansayo.core_model.course.CourseUploadEntity
import com.ddd.ansayo.domain.model.course.CoursePlaceImage
import com.ddd.ansayo.domain.model.course.CourseWriteAction
import com.ddd.ansayo.domain.model.course.CourseWriteMutation
import com.ddd.ansayo.domain.model.course.CourseWriteState
import com.ddd.ansayo.domain.usecase.course.CreateCourseUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class CourseWriteMutationHandler @Inject constructor(
    private val createCourseUseCase: CreateCourseUseCase
) {

    suspend fun mutate(
        state: CourseWriteState,
        action: CourseWriteAction
    ): Flow<CourseWriteMutation> = flow {
        when (action) {
            is CourseWriteAction.InputCourseTitle -> {
                emit(
                    CourseWriteMutation.Mutation.UpdateCourseTitle(
                        title = action.text,
                        isCourseTitleMaxInputted = action.text.length == COURSE_TITLE_MAX_LENGTH
                    )
                )
                emit(
                    CourseWriteMutation.Mutation.UpdateCompleteButton(
                        isConfirmButtonEnabled = action.text.isNotEmpty() && state.places.isNotEmpty()
                    )
                )
            }

            is CourseWriteAction.InputCourseDescription -> {
                emit(
                    CourseWriteMutation.Mutation.UpdateCourseDescription(
                        description = action.text,
                        isCourseDescriptionMaxInputted = action.text.length == COURSE_DESCRIPTION_MAX_LENGTH
                    )
                )
            }

            is CourseWriteAction.SelectDate -> {
                emit(CourseWriteMutation.Mutation.UpdateCourseDate(date = action.time))
            }

            is CourseWriteAction.SelectImages -> {
                val newPlaces = state.places.map { place ->
                    if (place.order == action.placeOrder) {
                        val newImages = (place.images + action.images.map { CoursePlaceImage(origin = it) })
                        place.copy(images = newImages)
                    } else {
                        place
                    }
                }
                emit(CourseWriteMutation.Mutation.AddPlaceImages(places = newPlaces))
            }

            is CourseWriteAction.ClickDeletePlace -> {
                val newPlaces = state.places.filterNot { place -> place.order == action.placeOrder }
                emit(CourseWriteMutation.Mutation.DeletePlace(places = newPlaces))
                emit(
                    CourseWriteMutation.Mutation.UpdateCompleteButton(
                        isConfirmButtonEnabled = state.header.title.isNotEmpty() && newPlaces.isNotEmpty()
                    )
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
                emit(CourseWriteMutation.Mutation.DeletePlaceImage(places = newPlaces))
            }

            is CourseWriteAction.InputPlaceReview -> {
                val newPlaces = state.places.map { place ->
                    if (place.order == action.placeOrder) {
                        place.copy(
                            review = action.text,
                            isPlaceReviewMaxInputted = action.text.length == PLACE_REVIEW_MAX_LENGTH
                        )
                    } else {
                        place
                    }
                }
                emit(CourseWriteMutation.Mutation.UpdatePlaceReview(places = newPlaces))
            }

            is CourseWriteAction.ToggleVisibilitySwitch -> {
                emit(CourseWriteMutation.Mutation.UpdateCourseVisibility(isPrivate = action.checked))
            }

            CourseWriteAction.ClickDatePicker -> {
                emit(CourseWriteMutation.SideEffect.ShowDatePickerDialog)
            }

            CourseWriteAction.ClickAddPlace -> {
                if (state.places.size == COURSE_PLACE_MAX_COUNT) {
                    emit(CourseWriteMutation.SideEffect.ShowSnackBar("장소는 코스당 최대 ${COURSE_PLACE_MAX_COUNT}개 까지 추가할 수 있어요."))
                } else {
                    emit(CourseWriteMutation.SideEffect.GoToAddPlace)
                }
            }

            is CourseWriteAction.ClickAddPlaceImage -> {
                val currentImagesCount =
                    state.places.first { it.order == action.placeOrder }.images.size
                if (currentImagesCount == PLACE_IMAGE_MAX_COUNT) {
                    emit(CourseWriteMutation.SideEffect.ShowSnackBar("이미지는 장소당 최대${PLACE_IMAGE_MAX_COUNT}장 까지 추가할 수 있어요."))
                } else {
                    val remainCount = PLACE_IMAGE_MAX_COUNT - currentImagesCount
                    emit(
                        CourseWriteMutation.SideEffect.ShowPhotoPicker(
                            order = action.placeOrder,
                            remainCount = remainCount
                        )
                    )
                }
            }

            is CourseWriteAction.ClickUploadButton -> {
                emit(CourseWriteMutation.SideEffect.ShowLoading)
                val result = when (val response = createCourseUseCase.invoke(
                    places = state.places,
                    request = CourseUploadEntity.Request(
                        date = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA).format(Date(state.header.date)),
                        isPublic = state.footer.isPrivate.not(),
                        name = state.header.title,
                        placeReviews = listOf(),
                        review = state.header.description
                    )
                )) {
                    is Response.Fail -> {
                        CourseWriteMutation.SideEffect.ShowSnackBar(response.message)
                    }
                    is Response.Success -> {
                        CourseWriteMutation.SideEffect.Finish
                    }
                }
                emit(CourseWriteMutation.SideEffect.HideLoading)
                emit(result)
            }

            is CourseWriteAction.SelectPlace -> {
                val newList = state.places + CourseWriteState.Place(
                    id = action.placeInfo.placeId,
                    order = state.places.size + 1,
                    title = action.placeInfo.name,
                    address = action.placeInfo.formattedAddress,
                    category = action.placeInfo.types,
                    isPlaceReviewMaxInputted = false,
                    review = "",
                    images = emptyList()
                )
                emit(CourseWriteMutation.Mutation.AddPlace(newList))
                emit(
                    CourseWriteMutation.Mutation.UpdateCompleteButton(
                        isConfirmButtonEnabled = state.header.title.isNotEmpty() && newList.isNotEmpty()
                    )
                )
            }
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
