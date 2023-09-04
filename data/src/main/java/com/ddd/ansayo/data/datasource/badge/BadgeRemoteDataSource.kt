package com.ddd.ansayo.data.datasource.badge

import com.ddd.ansayo.core_model.common.Response
import com.ddd.ansayo.core_model.search.SearchCourseEntity

interface BadgeRemoteDataSource {
    suspend fun getSearchableBadge(): Response<SearchCourseEntity.Response>

}