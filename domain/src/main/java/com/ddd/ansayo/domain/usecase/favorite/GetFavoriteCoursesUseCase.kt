package com.ddd.ansayo.domain.usecase.favorite

import com.ddd.ansayo.core_model.common.Response
import com.ddd.ansayo.domain.model.course.Course
import javax.inject.Inject

class GetFavoriteCoursesUseCase @Inject constructor() {

    suspend operator fun invoke() : Response<List<Course>> {
        TODO()
    }
}
