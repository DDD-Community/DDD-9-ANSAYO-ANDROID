package com.ddd.ansayo.domain.model.home

sealed class HomeAction {
    object ClickRecordButton : HomeAction()
    object ClickSearchButton : HomeAction()
    data class ClickBadgeCategory(val badgeId: String) : HomeAction()
    data class ClickBadgeCourse(val courseId: String) : HomeAction()
    data class ClickPopularCourse(val courseId: String) : HomeAction()

}
