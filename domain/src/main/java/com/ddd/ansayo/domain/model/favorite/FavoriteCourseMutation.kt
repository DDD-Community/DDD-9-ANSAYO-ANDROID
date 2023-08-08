package com.ddd.ansayo.domain.model.favorite

import com.ddd.ansayo.domain.model.course.Course

sealed class FavoriteCourseMutation {
    sealed class Mutation : FavoriteCourseMutation() {
        data class UpdateCourses(val courses: List<Course>, val hasFavorites: Boolean) : Mutation()
        data class UpdateFavorite(val courses: List<Course>) : Mutation()
    }

    sealed class SideEffect : FavoriteCourseMutation() {
        object NaviToSearch : SideEffect()
        data class NaviToCourseDetail(val id: String) : SideEffect()
        data class ShowSnackBar(val message: String) : SideEffect()
    }
}
