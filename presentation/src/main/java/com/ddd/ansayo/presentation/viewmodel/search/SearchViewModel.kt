package com.ddd.ansayo.presentation.viewmodel.search

import androidx.lifecycle.ViewModel
import com.ddd.ansayo.domain.handler.search.SearchMutationHandler
import com.ddd.ansayo.domain.model.search.keyword.SearchAction
import com.ddd.ansayo.domain.model.search.keyword.SearchMutation
import com.ddd.ansayo.domain.model.search.keyword.SearchState
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val mutationHandler: SearchMutationHandler
): ContainerHost<SearchState, SearchMutation.SideEffect>, ViewModel() {
    override val container: Container<SearchState, SearchMutation.SideEffect> =
        container(SearchState.EMPTY)


    fun onAction(action: SearchAction) = intent {
        mutationHandler.mutate(state,action)
            .collect() { muatation ->
                when(muatation) {
                    is SearchMutation.Mutation -> reduce(muatation)
                    is SearchMutation.SideEffect -> postSideEffect(muatation)
                }
            }
    }
    private fun reduce(mutation: SearchMutation.Mutation) {
        intent {
            reduce {
                when(mutation) {
                    is SearchMutation.Mutation.UpdateRecentKeyword -> {
                        state.copy(
                            keyword = mutation.keyword
                        )
                    }
                }
            }
        }
    }
}