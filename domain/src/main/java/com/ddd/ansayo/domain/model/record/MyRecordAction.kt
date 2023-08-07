package com.ddd.ansayo.domain.model.record

sealed class MyRecordAction {
    object SelectMyRecordTab : MyRecordAction()
    object ClickCourseWriteShortCutButton : MyRecordAction()
    object ClickCourseWriteButton : MyRecordAction()
    data class ClickCourse(val id: String) : MyRecordAction()
    object CompleteCourseWrite : MyRecordAction()
}
