package com.ddd.ansayo.domain.handler.favorite

import com.ddd.ansayo.core_model.common.Response
import com.ddd.ansayo.domain.model.favorite.FavoritePlaceAction
import com.ddd.ansayo.domain.model.favorite.FavoritePlaceMutation
import com.ddd.ansayo.domain.model.favorite.FavoritePlaceState
import com.ddd.ansayo.domain.usecase.favorite.GetFavoritePlacesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FavoritePlaceMutationHandler @Inject constructor(
    private val getFavoritePlacesUseCase: GetFavoritePlacesUseCase
) {

    suspend fun mutate(
        state: FavoritePlaceState,
        action: FavoritePlaceAction
    ) : Flow<FavoritePlaceMutation> {
        return flow {
            when (action) {
                is FavoritePlaceAction.ClickFavorite -> {
                    // TODO()
                    val places = state.places.map {
                        if (it.placeId == action.id) it
                        else it
                    }
                    emit(FavoritePlaceMutation.Mutation.UpdateFavorite(places))
                }
                FavoritePlaceAction.ClickFindPlace -> {
                    emit(FavoritePlaceMutation.SideEffect.NaviToSearch)
                }
                is FavoritePlaceAction.ClickPlace -> {
                    emit(FavoritePlaceMutation.SideEffect.NaviToPlaceDetail(action.id))
                }
                FavoritePlaceAction.SelectFavoriteListTab -> {
                    when (val result = getFavoritePlacesUseCase()) {
                        is Response.Fail -> {
                            emit(FavoritePlaceMutation.SideEffect.ShowSnackBar(result.message))
                        }
                        is Response.Success -> {
                            emit(
                                FavoritePlaceMutation.Mutation.UpdatePlaces(
                                    places = result.data,
                                    hasFavorites = result.data.isNotEmpty()
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}
