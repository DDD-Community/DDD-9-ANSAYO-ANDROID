package com.ddd.ansayo.domain.model.place

sealed class SearchAddPlaceAction {
    object ClickBackButton : SearchAddPlaceAction()
    data class SearchKeyword(val searchKeyword: String) : SearchAddPlaceAction()
    data class ClickSeeDetail(val placeId: String) : SearchAddPlaceAction()
    data class ClickAddPlace(val placeId: String) : SearchAddPlaceAction()
}
