package com.ddd.ansayo.domain.usecase.course

import com.ddd.ansayo.core_model.common.Response
import com.ddd.ansayo.core_model.search.SearchCourseEntity
import com.ddd.ansayo.domain.repository.CourseRepository
import javax.inject.Inject

class GetPopularCourseUseCase @Inject constructor(
    private val courseRepository: CourseRepository
){
    suspend operator fun invoke(badgeId: String) : Response<SearchCourseEntity.Response> {
        return courseRepository.getPopularCourses(badgeId)
    }
}