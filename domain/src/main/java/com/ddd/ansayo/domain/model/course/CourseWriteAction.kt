package com.ddd.ansayo.domain.model.course

sealed class CourseWriteAction {
    data class InputCourseTitle(val text: String) : CourseWriteAction()
    data class InputCourseDescription(val text: String) : CourseWriteAction()
    object ClickDatePicker : CourseWriteAction()
    data class SelectDate(val date: String) : CourseWriteAction()
    object ClickAddPlace : CourseWriteAction()
    data class ClickAddPlaceImage(val placeOrder: Int) : CourseWriteAction()
    data class SelectImages(val placeOrder: Int, val images: List<String>) : CourseWriteAction()
    data class InputPlaceReview(val placeOrder: Int, val text: String) : CourseWriteAction()
    data class ClickDeletePlace(val placeOrder: Int) : CourseWriteAction()
    data class ClickDeletePlaceImage(val placeOrder: Int, val imageIndex: Int) : CourseWriteAction()
    data class ToggleVisibilitySwitch(val checked: Boolean) : CourseWriteAction()
    data class ClickUploadButton(
        val courseTitle: String,
        val courseDescription: String,
        val placeReviews: List<String>
    ) : CourseWriteAction()
}
