package com.ddd.ansayo.domain.model.course.info

import com.ddd.ansayo.core_model.course.CourseInfo
import com.ddd.ansayo.core_model.place.Place

sealed class CourseInfoMutation {
    sealed class Mutation : CourseInfoMutation() {
        data class UpdateCourseInfo(val courseInfo: CourseInfo) : Mutation()
        data class UpdateFavorite(val places: List<Place>) : Mutation()
    }

    sealed class SideEffect : CourseInfoMutation() {
        object BackScreen: SideEffect()
        data class NaviToPlaceDetail(val id: String) : SideEffect()
    }

}
