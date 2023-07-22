package com.ddd.ansayo.data.datasource.course

import com.ddd.ansayo.data.model.course.UploadImageUrlData

interface CourseRemoteDataSource {

    suspend fun getUploadImageUrl(code: String, fileName: String): UploadImageUrlData
}
