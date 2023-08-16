package com.ddd.ansayo.domain.model.search

import com.ddd.ansayo.core_model.course.Course


data class SearchCourseState(
    val keyword: String,
    val courses: List<Course>
) {
    companion object {
        val EMPTY = SearchCourseState(
            keyword = "",
            courses = listOf()
        )
    }

}

