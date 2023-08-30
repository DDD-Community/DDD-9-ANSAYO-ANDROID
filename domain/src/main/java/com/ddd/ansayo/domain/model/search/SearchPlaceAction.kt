package com.ddd.ansayo.domain.model.search

sealed class SearchPlaceAction {
    object ClickBackButton : SearchPlaceAction()
    object SelectSearchListTab : SearchPlaceAction()
    data class SearchKeyword(val searchKeyword: String): SearchPlaceAction()
    data class ClickPlaceList (val id: String): SearchPlaceAction()
}
