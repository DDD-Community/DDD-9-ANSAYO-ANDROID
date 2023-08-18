package com.ddd.ansayo.domain.usecase.course

import com.ddd.ansayo.core_model.common.Response
import com.ddd.ansayo.core_model.course.CourseInfo
import com.ddd.ansayo.domain.repository.CourseRepository
import javax.inject.Inject

class GetCourseInfoUseCase @Inject constructor(
    private val courseRepository: CourseRepository
) {
    suspend operator fun invoke(courseId: String) : Response<CourseInfo> {
        return courseRepository.getCourseInfo(courseId)
    }
}