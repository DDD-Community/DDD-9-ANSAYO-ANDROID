package com.ddd.ansayo.domain.model.favorite

import com.ddd.ansayo.core_model.place.Place

sealed class FavoritePlaceMutation {
    sealed class Mutation : FavoritePlaceMutation() {
        data class UpdatePlaces(val places: List<Place>) : Mutation()
        data class UpdateFavorite(val places: List<Place>) : Mutation()
    }

    sealed class SideEffect : FavoritePlaceMutation() {
        object NaviToSearch : SideEffect()
        data class NaviToPlaceDetail(val id: String) : SideEffect()
        data class ShowSnackBar(val message: String) : SideEffect()
    }
}
