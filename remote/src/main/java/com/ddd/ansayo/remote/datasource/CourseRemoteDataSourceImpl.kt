package com.ddd.ansayo.remote.datasource

import android.content.Context
import android.net.Uri
import androidx.core.net.toUri
import com.ddd.ansayo.core_model.common.Response
import com.ddd.ansayo.core_model.course.CourseInfo
import com.ddd.ansayo.core_model.course.CourseUploadEntity
import com.ddd.ansayo.core_model.course.FavoriteCoursesEntity
import com.ddd.ansayo.core_model.course.UploadImageUrlEntity
import com.ddd.ansayo.core_model.search.SearchCourseEntity
import com.ddd.ansayo.data.datasource.course.CourseRemoteDataSource
import com.ddd.ansayo.remote.model.ContentUriRequestBody
import com.ddd.ansayo.remote.service.CourseService
import com.ddd.ansayo.remote.service.FileService
import com.ddd.ansayo.remote.util.toResponse
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject

class CourseRemoteDataSourceImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val courseService: CourseService,
    private val fileService: FileService
) : CourseRemoteDataSource {

    override suspend fun getFavoriteCourses(): Response<FavoriteCoursesEntity.Response> {
        return courseService.getFavoriteCourses().toResponse()
    }

    override suspend fun getCourseInfo(courseId: String): Response<CourseInfo> {
        return courseService.getCourseInfo(courseId).toResponse()
    }

    override suspend fun postFavoriteCourse(body: FavoriteCoursesEntity.PostRequest): Response<Unit> {
        return courseService.postFavoriteCourse(body).toResponse()
    }

    override suspend fun deleteFavoriteCourse(courseId: String): Response<Unit> {
        return courseService.deleteFavoriteCourse(courseId).toResponse()
    }

    override suspend fun getSearchCourses(query: String): Response<SearchCourseEntity.Response> {
        return courseService.getSearchCourse(query).toResponse()
    }
    override suspend fun getPopularCourses(badgeId: String): Response<SearchCourseEntity.Response> {
        return courseService.getPopularCourse(badgeId).toResponse()
    }

    override suspend fun getRecommendCourses(): Response<SearchCourseEntity.Response> {
        return courseService.getRecommendCourse().toResponse()
    }
    override suspend fun uploadImage(contentUri: String): Response<UploadImageUrlEntity> {
        return fileService.uploadImage(
            fileType = getFileType(contentUri.toUri()),
            file = MultipartBody.Part.createFormData(
                "image",
                System.currentTimeMillis().toString(),
                ContentUriRequestBody(context.contentResolver, contentUri.toUri())
            )
        ).toResponse()
    }
    private fun getFileType(contentUri: Uri): RequestBody {
        val mimeType = context.contentResolver.getType(contentUri) ?: throw Exception("")
        return mimeType.toRequestBody("text/plain".toMediaTypeOrNull())
    }

    override suspend fun postCourse(body: CourseUploadEntity.Request): Response<CourseUploadEntity.Response> {
        return courseService.postCourse(body).toResponse()
    }
}
