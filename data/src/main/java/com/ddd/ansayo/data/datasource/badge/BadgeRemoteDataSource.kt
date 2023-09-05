package com.ddd.ansayo.data.datasource.badge

import com.ddd.ansayo.core_model.badge.BadgeEntity
import com.ddd.ansayo.core_model.common.Response

interface BadgeRemoteDataSource {
    suspend fun getSearchableBadge(): Response<BadgeEntity.Response>

}