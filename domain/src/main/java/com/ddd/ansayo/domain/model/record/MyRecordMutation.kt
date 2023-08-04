package com.ddd.ansayo.domain.model.record

import com.ddd.ansayo.domain.model.course.Course

sealed class MyRecordMutation {
    sealed class Mutation : MyRecordMutation() {
        data class UpdateRecords(
            val hasRecord: Boolean,
            val courses: List<Course>
        ) : Mutation()
    }

    sealed class SideEffect : MyRecordMutation() {
        object NaviToCourseWrite : SideEffect()
    }
}
