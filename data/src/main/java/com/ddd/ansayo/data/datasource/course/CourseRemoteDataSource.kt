package com.ddd.ansayo.data.datasource.course

import com.ddd.ansayo.core_model.common.Response
import com.ddd.ansayo.core_model.course.FavoriteCoursesEntity
import com.ddd.ansayo.core_model.course.UploadImageUrlEntity
import com.ddd.ansayo.core_model.search.SearchCourseEntity

interface CourseRemoteDataSource {

    suspend fun getUploadImageUrl(code: String, fileName: String): Response<UploadImageUrlEntity>

    suspend fun getFavoriteCourses(): Response<FavoriteCoursesEntity.Response>

    suspend fun postFavoriteCourse(body: FavoriteCoursesEntity.PostRequest): Response<Unit>

    suspend fun deleteFavoriteCourse(courseId: String): Response<Unit>

    suspend fun getSearchCourses(query: String): Response<SearchCourseEntity.Response>

}
