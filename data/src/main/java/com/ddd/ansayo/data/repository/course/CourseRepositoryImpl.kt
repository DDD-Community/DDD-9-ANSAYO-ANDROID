package com.ddd.ansayo.data.repository.course

import com.ddd.ansayo.data.datasource.course.CourseRemoteDataSource
import com.ddd.ansayo.data.util.toResponse
import com.ddd.ansayo.domain.entity.course.UploadImageUrlEntity
import com.ddd.ansayo.domain.model.common.Response
import com.ddd.ansayo.domain.repository.CourseRepository
import javax.inject.Inject

class CourseRepositoryImpl @Inject constructor(
    private val courseRemoteDataSource: CourseRemoteDataSource
) : CourseRepository {

    override suspend fun getUploadImageUrl(
        code: String,
        fileName: String
    ): Response<UploadImageUrlEntity> {
        return courseRemoteDataSource.getUploadImageUrl(code, fileName).toResponse()
    }
}
