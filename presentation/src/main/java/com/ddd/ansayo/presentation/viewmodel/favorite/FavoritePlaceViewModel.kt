package com.ddd.ansayo.presentation.viewmodel.favorite

import androidx.lifecycle.ViewModel
import com.ddd.ansayo.domain.handler.favorite.FavoritePlaceMutationHandler
import com.ddd.ansayo.domain.model.favorite.FavoritePlaceAction
import com.ddd.ansayo.domain.model.favorite.FavoritePlaceMutation
import com.ddd.ansayo.domain.model.favorite.FavoritePlaceState
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class FavoritePlaceViewModel @Inject constructor(
    private val mutationHandler: FavoritePlaceMutationHandler
) : ContainerHost<FavoritePlaceState, FavoritePlaceMutation.SideEffect>, ViewModel() {

    override val container: Container<FavoritePlaceState, FavoritePlaceMutation.SideEffect> =
        container(FavoritePlaceState.EMPTY)

    fun onAction(action: FavoritePlaceAction) = intent {
        mutationHandler.mutate(state, action)
            .collect { mutation ->
                when (mutation) {
                    is FavoritePlaceMutation.Mutation -> reduce(mutation)
                    is FavoritePlaceMutation.SideEffect -> postSideEffect(mutation)
                }
            }
    }

    private fun reduce(mutation: FavoritePlaceMutation.Mutation) {
        intent {
            reduce {
                when (mutation) {
                    is FavoritePlaceMutation.Mutation.UpdateFavorite -> {
                        state.copy(places = mutation.places)
                    }
                    is FavoritePlaceMutation.Mutation.UpdatePlaces -> {
                        state.copy(
                            hasFavorites = mutation.hasFavorites,
                            places = mutation.places
                        )
                    }
                }
            }
        }
    }
}
