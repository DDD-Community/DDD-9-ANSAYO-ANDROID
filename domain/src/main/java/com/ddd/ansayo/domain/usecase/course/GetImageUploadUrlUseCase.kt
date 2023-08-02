package com.ddd.ansayo.domain.usecase.course

import com.ddd.ansayo.core_model.course.UploadImageUrlEntity
import com.ddd.ansayo.core_model.common.Response
import com.ddd.ansayo.domain.repository.CourseRepository
import com.ddd.ansayo.domain.util.toResponse
import javax.inject.Inject

class GetImageUploadUrlUseCase @Inject constructor(
    private val courseRepository: CourseRepository
) {

    suspend operator fun invoke(code: String, fileName: String): Response<UploadImageUrlEntity> {
        return courseRepository.getUploadImageUrl(code, fileName).toResponse()
    }
}
