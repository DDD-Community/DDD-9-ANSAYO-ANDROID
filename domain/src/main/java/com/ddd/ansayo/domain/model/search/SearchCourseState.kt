package com.ddd.ansayo.domain.model.search

import com.ddd.ansayo.domain.model.course.Course

data class SearchCourseState(
    val courses: List<Course>
) {
    companion object {
        val EMPTY = SearchCourseState(
            courses = emptyList()
        )
    }
}
