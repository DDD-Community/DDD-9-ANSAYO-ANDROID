package com.ddd.ansayo.remote.datasource

import com.ddd.ansayo.core_model.course.UploadImageUrlEntity
import com.ddd.ansayo.data.datasource.course.CourseRemoteDataSource
import com.ddd.ansayo.remote.service.CourseService
import com.skydoves.sandwich.getOrThrow
import javax.inject.Inject

class CourseRemoteDataSourceImpl @Inject constructor(
    private val courseService: CourseService
) : CourseRemoteDataSource {

    override suspend fun getUploadImageUrl(
        code: String,
        fileName: String
    ): UploadImageUrlEntity {
        return courseService.getImageUploadUrl(code, fileName).getOrThrow()
    }
}
