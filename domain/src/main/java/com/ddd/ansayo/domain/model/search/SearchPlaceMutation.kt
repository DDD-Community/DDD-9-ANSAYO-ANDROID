package com.ddd.ansayo.domain.model.search

import com.ddd.ansayo.core_model.place.Place

sealed class SearchPlaceMutation {
    sealed class Mutation: SearchPlaceMutation() {
        data class UpdatePlace(val place: List<Place>): Mutation()
    }
    sealed class SideEffect: SearchPlaceMutation() {
        object BackScreen: SideEffect()
        object NavToCourse: SideEffect()
        object SerachScreen: SideEffect()
        data class StartPlaceDetail(val placeId: String): SideEffect()
        data class ShowSnackBar(val message: String) : SideEffect()

    }
}
