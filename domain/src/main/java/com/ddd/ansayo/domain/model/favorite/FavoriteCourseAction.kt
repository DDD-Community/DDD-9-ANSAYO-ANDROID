package com.ddd.ansayo.domain.model.favorite

sealed class FavoriteCourseAction {
    object SelectFavoriteListTab : FavoriteCourseAction()
    object ClickFindCourse : FavoriteCourseAction()
    data class ClickCourse(val id: String) : FavoriteCourseAction()
    data class ClickFavorite(val id: String, val like: Boolean) : FavoriteCourseAction()
}
