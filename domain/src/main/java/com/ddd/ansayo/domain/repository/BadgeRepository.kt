package com.ddd.ansayo.domain.repository

import com.ddd.ansayo.core_model.badge.Badge
import com.ddd.ansayo.core_model.common.Response

interface BadgeRepository {
    suspend fun getSearchableBadge(): Response<Badge>
}