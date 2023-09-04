package com.ddd.ansayo.domain.repository

import com.ddd.ansayo.core_model.common.Response
import com.ddd.ansayo.core_model.course.CourseInfo
import com.ddd.ansayo.core_model.course.CourseUploadEntity
import com.ddd.ansayo.core_model.course.FavoriteCoursesEntity
import com.ddd.ansayo.core_model.course.UploadImageUrlEntity
import com.ddd.ansayo.core_model.search.SearchCourseEntity

interface CourseRepository {

    suspend fun getFavoriteCourses(): Response<FavoriteCoursesEntity.Response>
    suspend fun getCourseInfo(courseId: String): Response<CourseInfo>
    suspend fun postFavoriteCourse(body: FavoriteCoursesEntity.PostRequest): Response<Unit>
    suspend fun deleteFavoriteCourse(courseId: String): Response<Unit>
    suspend fun getSearchCourses(query: String): Response<SearchCourseEntity.Response>
    suspend fun uploadImage(contentUri: String): Response<UploadImageUrlEntity>
    suspend fun postCourse(body: CourseUploadEntity.Request): Response<CourseUploadEntity.Response>
}
