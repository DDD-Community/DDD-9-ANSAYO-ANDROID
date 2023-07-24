package com.ddd.ansayo.domain.usecase.course

import com.ddd.ansayo.domain.entity.course.UploadImageUrlEntity
import com.ddd.ansayo.domain.model.common.Response
import com.ddd.ansayo.domain.repository.CourseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetImageUploadUrlUseCase @Inject constructor(
    private val courseRepository: CourseRepository
) {

    operator fun invoke(code: String, fileName: String): Flow<Response<UploadImageUrlEntity>> {
        return flow {
            val response = courseRepository.getUploadImageUrl(code, fileName)
            emit(response)
        }
    }
}
