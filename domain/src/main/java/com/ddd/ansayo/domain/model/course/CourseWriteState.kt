package com.ddd.ansayo.domain.model.course

data class CourseWriteState(
    val date: String,
    val places: List<CoursePlace>,
    val isPrivate: Boolean,
    val isCourseTitleMaxInputted: Boolean,
    val isCourseDescriptionMaxInputted: Boolean,
    val isConfirmButtonEnabled: Boolean
) {

    companion object {
        val EMPTY = CourseWriteState(
            date = "",
            places = emptyList(),
            isPrivate = false,
            isCourseTitleMaxInputted = false,
            isCourseDescriptionMaxInputted = false,
            isConfirmButtonEnabled = false
        )
    }
}
