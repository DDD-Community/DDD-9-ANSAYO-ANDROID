package com.ddd.ansayo.domain.model.search

sealed class SearchPlaceAction {
    object ClickBackButton : SearchPlaceAction()
    object SelectSearchListTab : SearchPlaceAction()
    data class InputPlaceSearchWord(val text: String) : SearchPlaceAction()
    data class ClickSearch(val text: String): SearchPlaceAction()
    data class ClickPlaceList (val id: String): SearchPlaceAction()
}
