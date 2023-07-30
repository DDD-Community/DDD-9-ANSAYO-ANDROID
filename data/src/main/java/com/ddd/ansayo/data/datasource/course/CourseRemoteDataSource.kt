package com.ddd.ansayo.data.datasource.course

import com.ddd.ansayo.core_model.course.UploadImageUrlEntity

interface CourseRemoteDataSource {

    suspend fun getUploadImageUrl(code: String, fileName: String): UploadImageUrlEntity
}
