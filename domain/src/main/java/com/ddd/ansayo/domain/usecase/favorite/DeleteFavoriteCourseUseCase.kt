package com.ddd.ansayo.domain.usecase.favorite

import com.ddd.ansayo.core_model.common.Response
import com.ddd.ansayo.domain.repository.CourseRepository
import javax.inject.Inject

class DeleteFavoriteCourseUseCase @Inject constructor(
    private val courseRepository: CourseRepository
) {

    suspend operator fun invoke(id: String) : Response<Unit> {
        return courseRepository.deleteFavoriteCourse(id)
    }
}
