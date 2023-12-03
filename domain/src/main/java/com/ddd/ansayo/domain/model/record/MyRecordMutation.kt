package com.ddd.ansayo.domain.model.record

import com.ddd.ansayo.core_model.course.Course

sealed class MyRecordMutation {
    sealed class Mutation : MyRecordMutation() {
        data class UpdateRecords(
            val hasRecord: Boolean,
            val courses: List<Course>?
        ) : Mutation()
    }

    sealed class SideEffect : MyRecordMutation() {
        object NaviToCourseWrite : SideEffect()
        data class NaviToCourseDetail(val id: String) : SideEffect()
    }
}
