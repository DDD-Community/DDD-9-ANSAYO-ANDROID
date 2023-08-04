package com.ddd.ansayo.domain.model.record

sealed class MyRecordAction {
    object SelectMyRecordTab : MyRecordAction()
    object ClickCourseWriteShortCutButton : MyRecordAction()
    object ClickCourseWriteButton : MyRecordAction()
}
