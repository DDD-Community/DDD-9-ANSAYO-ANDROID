package com.ddd.ansayo.domain.model.place

import com.ddd.ansayo.core_model.place.Place

sealed class SearchAddPlaceMutation {
    sealed class Mutation : SearchAddPlaceMutation() {
        data class UpdateSearchResult(val places: List<Place>) : Mutation()
    }

    sealed class SideEffect : SearchAddPlaceMutation() {
        object Finish : SideEffect()
        object ShowLoading : SideEffect()
        object HideLoading : SideEffect()
        data class GoDetail(val placeId: String) : SideEffect()
        data class ShowSnackBar(val message: String) : SideEffect()
        data class SendPlaceInfo(val place: Place) : SideEffect()
    }
}
