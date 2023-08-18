package com.ddd.ansayo.domain.repository

import com.ddd.ansayo.core_model.common.Response
import com.ddd.ansayo.core_model.search.SearchCourseEntity

interface CourseSearchRepository {
    suspend fun getSearchCourse(): Response<SearchCourseEntity.Response>
}