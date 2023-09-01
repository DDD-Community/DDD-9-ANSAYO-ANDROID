package com.ddd.ansayo.presentation.viewmodel.place

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.ddd.ansayo.domain.handler.search.SearchPlaceMutationHandler
import com.ddd.ansayo.domain.model.search.SearchPlaceAction
import com.ddd.ansayo.domain.model.search.SearchPlaceMutation
import com.ddd.ansayo.domain.model.search.SearchPlaceState
import com.ddd.ansayo.presentation.viewmodel.Constant
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class SearchPlaceViewModel @Inject constructor(
    private val mutationHandler: SearchPlaceMutationHandler,
): ContainerHost<SearchPlaceState, SearchPlaceMutation.SideEffect>, ViewModel() {

    override val container: Container<SearchPlaceState, SearchPlaceMutation.SideEffect> =
        container(SearchPlaceState.EMPTY)
    fun onAction(action: SearchPlaceAction) = intent {
        mutationHandler.mutate(state,action)
            .collect() { muatation ->
                when(muatation) {
                    is SearchPlaceMutation.Mutation -> reduce(muatation)
                    is SearchPlaceMutation.SideEffect -> postSideEffect(muatation)
                }
            }
    }
    private fun reduce(mutation: SearchPlaceMutation.Mutation) {
        intent {
            reduce {
                when(mutation) {
                    is SearchPlaceMutation.Mutation.UpdatePlace -> {
                        state.copy(
                            places = mutation.place
                        )
                    }
                }
            }
        }
    }

}