package com.ddd.ansayo.domain.model.record

import com.ddd.ansayo.domain.model.course.Course

data class MyRecordState(
    val hasRecord: Boolean,
    val courses: List<Course>
) {
    companion object {
        val EMPTY = MyRecordState(
            hasRecord = false,
            courses = listOf()
        )
    }
}
