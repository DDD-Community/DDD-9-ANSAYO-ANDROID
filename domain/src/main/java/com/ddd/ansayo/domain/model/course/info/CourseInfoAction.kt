package com.ddd.ansayo.domain.model.course.info


sealed class CourseInfoAction {
    object BackScreen : CourseInfoAction()
    data class ClickFavorite(val like: Boolean) : CourseInfoAction()
    data class EnteredScreen(val courseId: String) : CourseInfoAction()
    data class ClickPlace(val id: String) : CourseInfoAction()

}
