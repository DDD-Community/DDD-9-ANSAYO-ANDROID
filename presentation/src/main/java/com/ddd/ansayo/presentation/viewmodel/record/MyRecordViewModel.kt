package com.ddd.ansayo.presentation.viewmodel.record

import androidx.lifecycle.ViewModel
import com.ddd.ansayo.domain.handler.record.MyRecordMutationHandler
import com.ddd.ansayo.domain.model.record.MyRecordAction
import com.ddd.ansayo.domain.model.record.MyRecordMutation
import com.ddd.ansayo.domain.model.record.MyRecordState
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class MyRecordViewModel @Inject constructor(
    private val mutationHandler: MyRecordMutationHandler
) : ContainerHost<MyRecordState, MyRecordMutation.SideEffect>, ViewModel() {

    override val container: Container<MyRecordState, MyRecordMutation.SideEffect>
        = container(MyRecordState.EMPTY)

    fun onAction(action: MyRecordAction) = intent {
        mutationHandler.mutate(state, action)
            .collect { mutation ->
                when (mutation) {
                    is MyRecordMutation.Mutation -> reduce(mutation)
                    is MyRecordMutation.SideEffect -> postSideEffect(mutation)
                }
            }
    }

    private fun reduce(mutation: MyRecordMutation.Mutation) {
        intent {
            reduce {
                when (mutation) {
                    is MyRecordMutation.Mutation.UpdateRecords -> {
                        state.copy(
                            hasRecord = mutation.hasRecord,
                            courses = mutation.courses
                        )
                    }
                }
            }
        }
    }
}
