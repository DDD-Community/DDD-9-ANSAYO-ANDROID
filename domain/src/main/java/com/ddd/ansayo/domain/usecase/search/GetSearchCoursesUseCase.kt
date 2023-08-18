package com.ddd.ansayo.domain.usecase.search

import com.ddd.ansayo.core_model.common.Response
import com.ddd.ansayo.core_model.search.SearchCourseEntity
import com.ddd.ansayo.domain.repository.CourseRepository
import javax.inject.Inject

class GetSearchCoursesUseCase @Inject constructor(
    private val courseRepository: CourseRepository
){
    suspend operator fun invoke(query: String): Response<SearchCourseEntity.Response> {
        return courseRepository.getSearchCourses(query)
    }
}