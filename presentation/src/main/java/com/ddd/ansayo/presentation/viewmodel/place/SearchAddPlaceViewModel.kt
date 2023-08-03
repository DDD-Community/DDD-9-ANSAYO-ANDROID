package com.ddd.ansayo.presentation.viewmodel.place

import androidx.lifecycle.ViewModel
import com.ddd.ansayo.domain.handler.place.SearchAddPlaceMutationHandler
import com.ddd.ansayo.domain.model.place.SearchAddPlaceAction
import com.ddd.ansayo.domain.model.place.SearchAddPlaceMutation
import com.ddd.ansayo.domain.model.place.SearchAddPlaceState
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class SearchAddPlaceViewModel @Inject constructor(
    private val mutationHandler: SearchAddPlaceMutationHandler
) : ContainerHost<SearchAddPlaceState, SearchAddPlaceMutation.SideEffect>, ViewModel() {

    override val container: Container<SearchAddPlaceState, SearchAddPlaceMutation.SideEffect> =
        container(SearchAddPlaceState.EMPTY)

    fun onAction(action: SearchAddPlaceAction) = intent {
        mutationHandler.mutate(state, action)
            .collect { mutation ->
                when (mutation) {
                    is SearchAddPlaceMutation.Mutation -> reduce(mutation)
                    is SearchAddPlaceMutation.SideEffect -> postSideEffect(mutation)
                }
            }
    }

    private fun reduce(mutation: SearchAddPlaceMutation.Mutation) {
        intent {
            reduce {
                when (mutation) {
                    is SearchAddPlaceMutation.Mutation.UpdateSearchResult -> {
                        state.copy(places = mutation.places)
                    }
                }
            }
        }
    }
}
