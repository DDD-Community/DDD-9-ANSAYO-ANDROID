package com.ddd.ansayo.domain.model.home

import com.ddd.ansayo.core_model.badge.Badge
import com.ddd.ansayo.core_model.course.Course


sealed class HomeMutation {

    sealed class Mutation : HomeMutation() {
        data class GetSearchableBadge(val badge: List<Badge>): Mutation()
        data class GetPopularCourseBadge(val course: List<Course>): Mutation()
        data class GetRecommandCourseBadge(val course: List<Course>): Mutation()

    }

    sealed class SideEffect : HomeMutation() {
        object NaviToSearch : SideEffect()
        object NaviToRecord : SideEffect()
        data class NaviToCourseDetail(val courseId: String) : SideEffect()
        data class ShowSnackBar(val message: String) : SideEffect()
    }
}
