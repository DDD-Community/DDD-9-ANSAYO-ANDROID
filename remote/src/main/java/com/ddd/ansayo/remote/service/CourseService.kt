package com.ddd.ansayo.remote.service

import com.ddd.ansayo.core_model.course.Course
import com.ddd.ansayo.core_model.course.CourseInfo
import com.ddd.ansayo.core_model.course.CourseUploadEntity
import com.ddd.ansayo.core_model.course.FavoriteCoursesEntity
import com.ddd.ansayo.core_model.course.MyCourse
import com.ddd.ansayo.core_model.search.SearchCourseEntity
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface CourseService {

    @GET("app/course/my")
    suspend fun getMyCourses(): ApiResponse<MyCourse>

    @GET("app/course/favorite")
    suspend fun getFavoriteCourses(): ApiResponse<FavoriteCoursesEntity.Response>

    @GET("app/course")
    suspend fun getCourseInfo(
        @Query("course_id") courseId: String,
    ): ApiResponse<CourseInfo>

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

    @POST("app/course")
    suspend fun postCourse(
        @Body body: CourseUploadEntity.Request
    ): ApiResponse<CourseUploadEntity.Response>
}
