package com.ddd.ansayo.domain.model.home

import com.ddd.ansayo.core_model.badge.Badge
import com.ddd.ansayo.core_model.course.Course

data class HomeState(
    val badge: List<Badge>,
    val badgeCourse: List<Course>,
    val recommandCourse: List<Course>,
) {
    companion object {
        val EMPTY = HomeState(
            badge = emptyList(),
            badgeCourse = emptyList(),
            recommandCourse = emptyList()
        )
    }
}
