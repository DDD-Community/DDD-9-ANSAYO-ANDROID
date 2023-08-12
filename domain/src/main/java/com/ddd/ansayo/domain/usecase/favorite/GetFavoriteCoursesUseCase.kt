package com.ddd.ansayo.domain.usecase.favorite

import com.ddd.ansayo.core_model.common.Response
import com.ddd.ansayo.core_model.course.FavoriteCoursesEntity
import com.ddd.ansayo.domain.repository.CourseRepository
import com.ddd.ansayo.domain.util.toResponse
import javax.inject.Inject

class GetFavoriteCoursesUseCase @Inject constructor(
    private val courseRepository: CourseRepository
) {

    suspend operator fun invoke() : Response<FavoriteCoursesEntity.Response> {
        return courseRepository.getFavoriteCourses().toResponse()
    }
}
