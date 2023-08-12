package com.ddd.ansayo.data.datasource.course

import com.ddd.ansayo.core_model.course.FavoriteCoursesEntity
import com.ddd.ansayo.core_model.course.UploadImageUrlEntity

interface CourseRemoteDataSource {

    suspend fun getUploadImageUrl(code: String, fileName: String): UploadImageUrlEntity

    suspend fun getFavoriteCourses(): FavoriteCoursesEntity.Response

    suspend fun postFavoriteCourse(body: FavoriteCoursesEntity.PostRequest)

    suspend fun deleteFavoriteCourse(courseId: String)
}
