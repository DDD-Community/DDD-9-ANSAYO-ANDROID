package com.ddd.ansayo.domain.repository

import com.ddd.ansayo.core_model.course.UploadImageUrlEntity

interface CourseRepository {

    suspend fun getUploadImageUrl(code: String, fileName: String): UploadImageUrlEntity
}
