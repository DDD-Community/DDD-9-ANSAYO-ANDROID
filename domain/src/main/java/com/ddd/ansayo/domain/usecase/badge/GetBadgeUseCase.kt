package com.ddd.ansayo.domain.usecase.badge

import com.ddd.ansayo.core_model.common.Response
import com.ddd.ansayo.core_model.course.Course
import javax.inject.Inject

class GetBadgeUseCase @Inject constructor(
){
    suspend operator fun invoke() : Response<List<Course>> {
        TODO()
    }
}