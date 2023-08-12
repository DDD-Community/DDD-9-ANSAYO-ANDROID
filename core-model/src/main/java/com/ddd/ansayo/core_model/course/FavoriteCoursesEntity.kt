package com.ddd.ansayo.core_model.course

import com.google.gson.annotations.SerializedName

sealed class FavoriteCoursesEntity {

    data class PostRequest(
        @SerializedName("course_id") val courseId: String
    ) : FavoriteCoursesEntity()

    data class Response(
        val courses: List<Course>?
    ) : FavoriteCoursesEntity()
}
