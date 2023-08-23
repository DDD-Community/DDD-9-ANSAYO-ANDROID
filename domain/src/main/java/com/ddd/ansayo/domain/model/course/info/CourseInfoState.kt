package com.ddd.ansayo.domain.model.course.info

import com.ddd.ansayo.core_model.course.Course
import com.ddd.ansayo.core_model.course.CourseInfo

data class CourseInfoState(
    val course: Course,
    val courseInfo: CourseInfo
) {
    companion object {
        val EMPTY = CourseInfoState(
           course = Course(
               authorId = "",
               favorites = 0,
               id = "",
               name = "",
               isFavorite = false,
               regDate = "",
               review = ""
           ),
           courseInfo = CourseInfo(
               course = Course(
                   authorId = "",
                   favorites = 0,
                   id = "",
                   name = "",
                   isFavorite = false,
                   regDate = "",
                   review = ""
               ),
               placePhotos = emptyList(),
               placeReviews = emptyList(),
               places = emptyList()
           )
        )
    }
}
