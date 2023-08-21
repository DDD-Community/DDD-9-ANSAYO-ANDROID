package com.ddd.ansayo.remote.service

import com.ddd.ansayo.core_model.course.FavoriteCoursesEntity
import com.ddd.ansayo.core_model.course.UploadImageUrlEntity
import com.ddd.ansayo.core_model.search.SearchCourseEntity
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface CourseService {

    @GET("/image")
    suspend fun getImageUploadUrl(
        @Query("code") code: String,
        @Query("name") name: String
    ): ApiResponse<UploadImageUrlEntity>

    @GET("app/course/favorite")
    suspend fun getFavoriteCourses(): ApiResponse<FavoriteCoursesEntity.Response>

    @POST("/app/course/favorite")
    suspend fun postFavoriteCourse(
        @Body body: FavoriteCoursesEntity.PostRequest
    ): ApiResponse<Unit>

    @DELETE("/app/course/favorite")
    suspend fun deleteFavoriteCourse(
        @Query("course_id") courseId: String
    ): ApiResponse<Unit>

    @GET("app/course/search")
    suspend fun getSearchCourse(
        @Query("query") query: String
    ): ApiResponse<SearchCourseEntity.Response>
}
