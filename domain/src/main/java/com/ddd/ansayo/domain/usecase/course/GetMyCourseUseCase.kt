package com.ddd.ansayo.domain.usecase.course

import com.ddd.ansayo.core_model.common.Response
import com.ddd.ansayo.core_model.course.Course
import com.ddd.ansayo.core_model.course.MyCourse
import com.ddd.ansayo.domain.repository.CourseRepository
import javax.inject.Inject

class GetMyCourseUseCase @Inject constructor(
    private val courseRepository: CourseRepository
) {
    suspend operator fun invoke() : Response<MyCourse> {
        return courseRepository.getMyCourses()
    }
}
