package com.ddd.ansayo.domain.model.search


sealed class SearchCourseAction {
    object ClickBackButton : SearchCourseAction()
    object SelectSearchListTab : SearchCourseAction()
    object ClickRecordCourse : SearchCourseAction()
    data class SearchKeyword(val searchKeyword: String): SearchCourseAction()
    data class ClickCourseList(val id: String): SearchCourseAction()
}
