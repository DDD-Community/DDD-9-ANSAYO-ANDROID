package com.ddd.ansayo.domain.model.favorite

import com.ddd.ansayo.core_model.course.Course

data class FavoriteCourseState(
    val hasFavorites: Boolean,
    val courses: List<Course>
) {
    companion object {
        val EMPTY = FavoriteCourseState(
            hasFavorites = true,
            courses = emptyList()
        )
    }
}
