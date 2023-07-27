package com.ddd.ansayo.domain.usecase.course

import com.ddd.ansayo.domain.entity.course.UploadImageUrlEntity
import com.ddd.ansayo.domain.model.common.Response
import com.ddd.ansayo.domain.repository.CourseRepository
import javax.inject.Inject

class GetImageUploadUrlUseCase @Inject constructor(
    private val courseRepository: CourseRepository
) {

    suspend operator fun invoke(code: String, fileName: String): Response<UploadImageUrlEntity> {
        return courseRepository.getUploadImageUrl(code, fileName)
    }
}
