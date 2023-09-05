package com.ddd.ansayo.domain.model.course

sealed class CourseWriteMutation {
    sealed class Mutation : CourseWriteMutation() {
        data class UpdateCourseTitle(val title: String, val isCourseTitleMaxInputted: Boolean) : Mutation()
        data class UpdateCourseDescription(val description: String, val isCourseDescriptionMaxInputted: Boolean) : Mutation()
        data class UpdateCourseDate(val date: Long) : Mutation()
        data class AddPlaceImages(val places: List<CourseWriteState.Place>) : Mutation()
        data class DeletePlace(val places: List<CourseWriteState.Place>) : Mutation()
        data class DeletePlaceImage(val places: List<CourseWriteState.Place>) : Mutation()
        data class UpdatePlaceReview(val places: List<CourseWriteState.Place>) : Mutation()
        data class UpdateCourseVisibility(val isPrivate: Boolean) : Mutation()
        data class AddPlace(val places: List<CourseWriteState.Place>) : Mutation()
        data class UpdateCompleteButton(val isConfirmButtonEnabled: Boolean) : Mutation()
    }

    sealed class SideEffect : CourseWriteMutation() {
        object ShowLoading : SideEffect()
        object HideLoading : SideEffect()
        object ShowDatePickerDialog : SideEffect()
        data class ShowPhotoPicker(val order: Int, val remainCount: Int) : SideEffect()
        data class ShowSnackBar(val message: String) : SideEffect()
        object GoToAddPlace : SideEffect()
        object Finish : SideEffect()
    }
}
