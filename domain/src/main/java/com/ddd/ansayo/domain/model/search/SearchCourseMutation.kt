package com.ddd.ansayo.domain.model.search

import com.ddd.ansayo.core_model.course.Course


sealed class SearchCourseMutation {
    sealed class Mutation: SearchCourseMutation() {
        data class UpdateCourse(val course: List<Course>): Mutation()
    }
    sealed class SideEffect: SearchCourseMutation() {
        object StartCourseRecord : SideEffect()
        data class StartCourseDetail(val id: String): SideEffect()
    }
}
