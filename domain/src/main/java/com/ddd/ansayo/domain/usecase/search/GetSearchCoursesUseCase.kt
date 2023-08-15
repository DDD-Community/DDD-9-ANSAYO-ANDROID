package com.ddd.ansayo.domain.usecase.search

import com.ddd.ansayo.core_model.common.Response
import com.ddd.ansayo.core_model.search.SearchCourseEntity
import com.ddd.ansayo.domain.repository.CourseSearchRepository
import javax.inject.Inject

class GetSearchCoursesUseCase @Inject constructor(
    private val courseSearchRepository: CourseSearchRepository
){
    suspend operator fun invoke(): Response<SearchCourseEntity.Response> {
        return courseSearchRepository.getSearchCourse()
    }
}