package com.ddd.ansayo.domain.model.home

import com.ddd.ansayo.core_model.badge.Badge
import com.ddd.ansayo.core_model.course.Course

data class HomeState(
    val badge: Badge,
    val course: List<Course>,
)
