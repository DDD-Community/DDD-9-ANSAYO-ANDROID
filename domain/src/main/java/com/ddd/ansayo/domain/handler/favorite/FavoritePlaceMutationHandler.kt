package com.ddd.ansayo.domain.handler.favorite

import com.ddd.ansayo.core_model.common.Response
import com.ddd.ansayo.domain.model.favorite.FavoritePlaceAction
import com.ddd.ansayo.domain.model.favorite.FavoritePlaceMutation
import com.ddd.ansayo.domain.model.favorite.FavoritePlaceState
import com.ddd.ansayo.domain.usecase.favorite.DeleteFavoritePlaceUseCase
import com.ddd.ansayo.domain.usecase.favorite.GetFavoritePlacesUseCase
import com.ddd.ansayo.domain.usecase.favorite.PostFavoritePlaceUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FavoritePlaceMutationHandler @Inject constructor(
    private val getFavoritePlacesUseCase: GetFavoritePlacesUseCase,
    private val postFavoritePlaceUseCase: PostFavoritePlaceUseCase,
    private val deleteFavoritePlaceUseCase: DeleteFavoritePlaceUseCase
) {

    suspend fun mutate(
        state: FavoritePlaceState,
        action: FavoritePlaceAction
    ): Flow<FavoritePlaceMutation> {
        return flow {
            when (action) {
                is FavoritePlaceAction.ClickFavorite -> {
                    when (val result =
                        if (action.like) {
                            deleteFavoritePlaceUseCase(action.id)
                        } else {
                            postFavoritePlaceUseCase(action.id)
                        }
                    ) {
                        is Response.Success -> {
                            val places = state.places.map {
                                if (it.placeId == action.id) it.copy(isFavorite = action.like.not())
                                else it
                            }
                            emit(FavoritePlaceMutation.Mutation.UpdateFavorite(places))
                        }

                        is Response.Fail -> {
                            emit(FavoritePlaceMutation.SideEffect.ShowSnackBar(result.message))
                        }
                    }
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
                                    places = result.data.places.orEmpty(),
                                    hasFavorites = result.data.places.isNullOrEmpty().not()
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}
