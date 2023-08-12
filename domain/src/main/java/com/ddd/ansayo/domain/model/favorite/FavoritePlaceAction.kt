package com.ddd.ansayo.domain.model.favorite

sealed class FavoritePlaceAction {
    object SelectFavoriteListTab : FavoritePlaceAction()
    object ClickFindPlace : FavoritePlaceAction()
    data class ClickPlace(val id: String) : FavoritePlaceAction()
    data class ClickFavorite(val id: String, val like: Boolean) : FavoritePlaceAction()
}
