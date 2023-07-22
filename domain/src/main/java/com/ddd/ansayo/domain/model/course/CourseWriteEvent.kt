package com.ddd.ansayo.domain.model.course

sealed class CourseWriteEvent {
    data class InputCourseTitle(val text: String) : CourseWriteEvent()
    data class InputCourseDescription(val text: String) : CourseWriteEvent()
    object ClickDatePicker : CourseWriteEvent()
    data class SelectDate(val date: String) : CourseWriteEvent()
    object ClickAddPlace : CourseWriteEvent()
    data class ClickAddPlaceImage(val placeOrder: Int) : CourseWriteEvent()
    data class SelectImages(val placeOrder: Int, val images: List<String>) : CourseWriteEvent()
    data class InputPlaceReview(val placeOrder: Int, val text: String) : CourseWriteEvent()
    data class ClickDeletePlace(val placeOrder: Int) : CourseWriteEvent()
    data class ClickDeletePlaceImage(val placeOrder: Int, val imageIndex: Int) : CourseWriteEvent()
    data class ToggleVisibilitySwitch(val checked: Boolean) : CourseWriteEvent()
    object StartApiCall : CourseWriteEvent()
    object CompleteApiCall : CourseWriteEvent()
    object SuccessUpload : CourseWriteEvent()
    data class FailUpload(val message: String) : CourseWriteEvent()
}
