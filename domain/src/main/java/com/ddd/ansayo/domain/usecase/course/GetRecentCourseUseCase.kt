package com.ddd.ansayo.domain.usecase.course

import com.ddd.ansayo.core_model.common.Response
import com.ddd.ansayo.core_model.course.Course
import com.ddd.ansayo.core_model.course.RecentCourseEntity
import javax.inject.Inject

class GetRecentCourseUseCase @Inject constructor(
){
    suspend operator fun invoke() : Response<RecentCourseEntity> {
        TODO()
    }
}