package com.ddd.ansayo.remote.datasource

import com.ddd.ansayo.core_model.common.Response
import com.ddd.ansayo.core_model.search.SearchCourseEntity
import com.ddd.ansayo.data.datasource.badge.BadgeRemoteDataSource
import com.ddd.ansayo.remote.service.BadgeService
import com.ddd.ansayo.remote.util.toResponse
import javax.inject.Inject

class BadgeRemoteDataSourceImpl @Inject constructor(
    private val badgeService: BadgeService
) : BadgeRemoteDataSource {
    override suspend fun getSearchableBadge(): Response<SearchCourseEntity.Response> {
        return badgeService.getSearchableBadge().toResponse()
    }
}