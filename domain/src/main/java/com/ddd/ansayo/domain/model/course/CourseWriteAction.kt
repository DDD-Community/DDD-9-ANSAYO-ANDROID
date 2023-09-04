package com.ddd.ansayo.domain.model.course

import com.ddd.ansayo.core_model.place.AddPlaceInfoDto

sealed class CourseWriteAction {
    data class InputCourseTitle(val text: String) : CourseWriteAction()
    data class InputCourseDescription(val text: String) : CourseWriteAction()
    object ClickDatePicker : CourseWriteAction()
    data class SelectDate(val time: Long) : CourseWriteAction()
    object ClickAddPlace : CourseWriteAction()
    data class ClickAddPlaceImage(val placeOrder: Int) : CourseWriteAction()
    data class SelectImages(val placeOrder: Int, val images: List<String>) : CourseWriteAction()
    data class InputPlaceReview(val placeOrder: Int, val text: String) : CourseWriteAction()
    data class ClickDeletePlace(val placeOrder: Int) : CourseWriteAction()
    data class ClickDeletePlaceImage(val placeOrder: Int, val imageIndex: Int) : CourseWriteAction()
    data class ToggleVisibilitySwitch(val checked: Boolean) : CourseWriteAction()
    object ClickUploadButton : CourseWriteAction()
    data class SelectPlace(val placeInfo: AddPlaceInfoDto) : CourseWriteAction()
}
