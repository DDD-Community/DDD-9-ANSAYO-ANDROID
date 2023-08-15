package com.ddd.ansayo.domain.model.search


sealed class SearchCourseAction {
    object ClickBackButton : SearchCourseAction()
    object SelectSearchListTab : SearchCourseAction()
    data class InputPlaceSearchWord(val text: String) : SearchCourseAction()
    object ClickRecordCourse : SearchCourseAction()
    data class ClickSearch(val text: String): SearchCourseAction()
    data class ClickCourseList(val id: String): SearchCourseAction()
}
