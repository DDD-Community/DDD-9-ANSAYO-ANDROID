package com.ddd.ansayo.domain.model.course.info

import com.ddd.ansayo.core_model.course.Course
import com.ddd.ansayo.core_model.course.CourseInfo

sealed class CourseInfoMutation {
    sealed class Mutation : CourseInfoMutation() {
        data class UpdateCourseInfo(val courseInfo: CourseInfo) : Mutation()
        data class UpdateFavoriteCount(val course: Course) : Mutation()
    }

    sealed class SideEffect : CourseInfoMutation() {
        object BackScreen: SideEffect()
        data class NaviToPlaceDetail(val placeId: String) : SideEffect()
        data class ShowSnackBar(val message: String) : SideEffect()

    }

}
