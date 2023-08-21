package com.ddd.ansayo.domain.model.course.info


sealed class CourseInfoAction {
    object BackScreen : CourseInfoAction()
    data class ClickFavorite(val id: String, val like: Boolean) : CourseInfoAction()
    data class SelectCourseInfo(val courseId: String) : CourseInfoAction()
    data class NaviToPlaceDetail(val id: String) : CourseInfoAction()

}
