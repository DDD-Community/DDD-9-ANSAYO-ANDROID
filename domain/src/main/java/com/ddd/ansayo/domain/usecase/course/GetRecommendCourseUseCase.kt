package com.ddd.ansayo.domain.usecase.course

import com.ddd.ansayo.core_model.common.Response
import com.ddd.ansayo.core_model.course.RecommendCourseEntity
import javax.inject.Inject

class GetRecommendCourseUseCase @Inject constructor(
){
    suspend operator fun invoke() : Response<RecommendCourseEntity> {
        TODO()
    }
}