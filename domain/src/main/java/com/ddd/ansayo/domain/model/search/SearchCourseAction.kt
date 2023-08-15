package com.ddd.ansayo.domain.model.search


sealed class SearchCourseAction {
    object SelectSearchListTab : SearchCourseAction()
    data class ClickCourse(val id: String): SearchCourseAction()
}
