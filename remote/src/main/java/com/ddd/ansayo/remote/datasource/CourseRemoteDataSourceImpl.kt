package com.ddd.ansayo.remote.datasource

import com.ddd.ansayo.core_model.common.Response
import com.ddd.ansayo.core_model.course.Course
import com.ddd.ansayo.core_model.course.FavoriteCoursesEntity
import com.ddd.ansayo.core_model.course.SearchCourseEntity
import com.ddd.ansayo.core_model.course.UploadImageUrlEntity
import com.ddd.ansayo.data.datasource.course.CourseRemoteDataSource
import com.ddd.ansayo.remote.service.CourseService
import com.ddd.ansayo.remote.util.toResponse
import javax.inject.Inject

class CourseRemoteDataSourceImpl @Inject constructor(
    private val courseService: CourseService
) : CourseRemoteDataSource {

    override suspend fun getUploadImageUrl(
        code: String,
        fileName: String
    ): Response<UploadImageUrlEntity> {
        return courseService.getImageUploadUrl(code, fileName).toResponse()
    }

    override suspend fun getFavoriteCourses(): Response<FavoriteCoursesEntity.Response> {
        return courseService.getFavoriteCourses().toResponse()
    }

    override suspend fun postFavoriteCourse(body: FavoriteCoursesEntity.PostRequest): Response<Unit> {
        return courseService.postFavoriteCourse(body).toResponse()
    }

    override suspend fun deleteFavoriteCourse(courseId: String): Response<Unit> {
        return courseService.deleteFavoriteCourse(courseId).toResponse()
    }

    override suspend fun getSearchCourses(query: String): Response<SearchCourseEntity> {
        return courseService.getSearchCourse(query).toResponse()
    }
}
