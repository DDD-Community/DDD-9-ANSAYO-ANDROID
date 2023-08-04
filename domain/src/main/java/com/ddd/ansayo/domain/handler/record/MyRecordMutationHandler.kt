package com.ddd.ansayo.domain.handler.record

import com.ddd.ansayo.domain.model.record.MyRecordAction
import com.ddd.ansayo.domain.model.record.MyRecordMutation
import com.ddd.ansayo.domain.model.record.MyRecordState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MyRecordMutationHandler @Inject constructor() {

    suspend fun mutate(
        state: MyRecordState,
        action: MyRecordAction
    ): Flow<MyRecordMutation> {
        return flow {
            when (action) {
                MyRecordAction.ClickCourseWriteButton -> {
                    emit(MyRecordMutation.SideEffect.NaviToCourseWrite)
                }

                MyRecordAction.ClickCourseWriteShortCutButton -> {
                    emit(MyRecordMutation.SideEffect.NaviToCourseWrite)
                }

                MyRecordAction.SelectMyRecordTab -> {
                    emit(MyRecordMutation.Mutation.UpdateRecords(false, emptyList()))
                }
            }
        }
    }
}
