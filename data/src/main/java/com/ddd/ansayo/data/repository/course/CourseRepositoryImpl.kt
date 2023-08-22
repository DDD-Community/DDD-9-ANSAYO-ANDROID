package com.ddd.ansayo.data.repository.course

import com.ddd.ansayo.core_model.common.Response
import com.ddd.ansayo.core_model.course.CourseInfo
import com.ddd.ansayo.core_model.course.FavoriteCoursesEntity
import com.ddd.ansayo.core_model.course.UploadImageUrlEntity
import com.ddd.ansayo.core_model.search.SearchCourseEntity
import com.ddd.ansayo.data.datasource.course.CourseRemoteDataSource
import com.ddd.ansayo.domain.repository.CourseRepository
import javax.inject.Inject

class CourseRepositoryImpl @Inject constructor(
    private val courseRemoteDataSource: CourseRemoteDataSource
) : CourseRepository {

    override suspend fun getUploadImageUrl(
        code: String,
        fileName: String
    ): Response<UploadImageUrlEntity> {
        return courseRemoteDataSource.getUploadImageUrl(code, fileName)
    }

    override suspend fun getFavoriteCourses(): Response<FavoriteCoursesEntity.Response> {
        return courseRemoteDataSource.getFavoriteCourses()
    }

    override suspend fun getCourseInfo(courseId: String): Response<CourseInfo> {
        return courseRemoteDataSource.getCourseInfo(courseId)
    }

    override suspend fun postFavoriteCourse(body: FavoriteCoursesEntity.PostRequest): Response<Unit> {
        return courseRemoteDataSource.postFavoriteCourse(body)
    }

    override suspend fun deleteFavoriteCourse(courseId: String): Response<Unit> {
        return courseRemoteDataSource.deleteFavoriteCourse(courseId)
    }

    override suspend fun getSearchCourses(query: String): Response<SearchCourseEntity.Response> {
        return courseRemoteDataSource.getSearchCourses(query)
    }

}
