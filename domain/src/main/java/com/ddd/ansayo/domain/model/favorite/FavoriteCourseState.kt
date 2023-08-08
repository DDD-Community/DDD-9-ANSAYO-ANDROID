package com.ddd.ansayo.domain.model.favorite

import com.ddd.ansayo.domain.model.course.Course

data class FavoriteCourseState(
    val hasFavorites: Boolean,
    val courses: List<Course>
)
