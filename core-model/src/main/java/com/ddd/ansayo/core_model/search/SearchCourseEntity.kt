package com.ddd.ansayo.core_model.search

import com.ddd.ansayo.core_model.course.Course

sealed class SearchCourseEntity {

    data class Response(
        val courses: List<Course>?
    ) : SearchCourseEntity()

}
