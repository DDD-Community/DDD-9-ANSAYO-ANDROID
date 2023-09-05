package com.ddd.ansayo.data.repository.badge

import com.ddd.ansayo.core_model.badge.BadgeEntity
import com.ddd.ansayo.core_model.common.Response
import com.ddd.ansayo.data.datasource.badge.BadgeRemoteDataSource
import com.ddd.ansayo.domain.repository.BadgeRepository
import javax.inject.Inject

class BadgeRepositoryImpl @Inject constructor(
    private val badgeRemoteDataSource: BadgeRemoteDataSource
) : BadgeRepository {
    override suspend fun getSearchableBadge(): Response<BadgeEntity.Response> {
        return badgeRemoteDataSource.getSearchableBadge()
    }
}