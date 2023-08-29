package com.ddd.ansayo.domain.repository

import com.ddd.ansayo.core_model.common.Response
import com.ddd.ansayo.core_model.course.Course

interface BadgeRepository {
    suspend fun getBadge(): Response<List<Course>>
}