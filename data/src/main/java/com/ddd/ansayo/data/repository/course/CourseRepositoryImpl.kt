package com.ddd.ansayo.data.repository.course

import com.ddd.ansayo.core_model.course.FavoriteCoursesEntity
import com.ddd.ansayo.core_model.course.UploadImageUrlEntity
import com.ddd.ansayo.data.datasource.course.CourseRemoteDataSource
import com.ddd.ansayo.domain.repository.CourseRepository
import javax.inject.Inject

class CourseRepositoryImpl @Inject constructor(
    private val courseRemoteDataSource: CourseRemoteDataSource
) : CourseRepository {

    override suspend fun getUploadImageUrl(
        code: String,
        fileName: String
    ): UploadImageUrlEntity {
        return courseRemoteDataSource.getUploadImageUrl(code, fileName)
    }

    override suspend fun getFavoriteCourses(): FavoriteCoursesEntity.Response {
        return courseRemoteDataSource.getFavoriteCourses()
    }

    override suspend fun postFavoriteCourse(body: FavoriteCoursesEntity.PostRequest) {
        return courseRemoteDataSource.postFavoriteCourse(body)
    }

    override suspend fun deleteFavoriteCourse(courseId: String) {
        return courseRemoteDataSource.deleteFavoriteCourse(courseId)
    }
}
