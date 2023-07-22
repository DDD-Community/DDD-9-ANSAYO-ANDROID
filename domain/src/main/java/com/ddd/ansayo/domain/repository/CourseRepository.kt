package com.ddd.ansayo.domain.repository

import com.ddd.ansayo.domain.entity.course.UploadImageUrlEntity
import com.ddd.ansayo.domain.model.common.Response

interface CourseRepository {

    suspend fun getUploadImageUrl(code: String, fileName: String): Response<UploadImageUrlEntity>
}
