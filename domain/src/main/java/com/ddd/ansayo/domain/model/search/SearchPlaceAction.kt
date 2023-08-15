package com.ddd.ansayo.domain.model.search

sealed class SearchPlaceAction {
    object SelectSearchListTab : SearchPlaceAction()
    data class ClickPlace(val id: String): SearchPlaceAction()
}
