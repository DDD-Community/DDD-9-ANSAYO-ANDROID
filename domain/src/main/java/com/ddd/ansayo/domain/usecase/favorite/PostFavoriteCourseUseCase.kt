package com.ddd.ansayo.domain.usecase.favorite

import com.ddd.ansayo.core_model.common.Response
import com.ddd.ansayo.core_model.course.FavoriteCoursesEntity
import com.ddd.ansayo.domain.repository.CourseRepository
import javax.inject.Inject

class PostFavoriteCourseUseCase @Inject constructor(
    private val courseRepository: CourseRepository
) {

    suspend operator fun invoke(id: String): Response<Unit> {
        return courseRepository.postFavoriteCourse(FavoriteCoursesEntity.PostRequest(id))
    }
}
