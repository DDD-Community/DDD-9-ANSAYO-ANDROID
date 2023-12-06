package com.ddd.ansayo.domain.handler.record

import com.ddd.ansayo.core_model.common.Response
import com.ddd.ansayo.domain.model.record.MyRecordAction
import com.ddd.ansayo.domain.model.record.MyRecordMutation
import com.ddd.ansayo.domain.model.record.MyRecordState
import com.ddd.ansayo.domain.usecase.course.GetMyCourseUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MyRecordMutationHandler @Inject constructor(
    private val myCourseUseCase: GetMyCourseUseCase
) {

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
                    when (val myCourse = myCourseUseCase()) {
                        is Response.Success -> {
                            emit(MyRecordMutation.Mutation.UpdateRecords(myCourse.data.courses != null, myCourse.data.courses))
                        }
                        is Response.Fail -> {
                            emit(MyRecordMutation.Mutation.UpdateRecords(false, emptyList()))
                        }
                    }

                }

                is MyRecordAction.ClickCourse -> {
                    emit(MyRecordMutation.SideEffect.NaviToCourseDetail(action.id))
                }

                MyRecordAction.CompleteCourseWrite -> {
                    emit(MyRecordMutation.Mutation.UpdateRecords(false, emptyList()))
                }
            }
        }
    }
}
