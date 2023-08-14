package com.ddd.ansayo.domain.model.place

sealed class PlaceDetailAction {
    data class EnteredScreen(val placeId: String) : PlaceDetailAction()
    object ClickBackButton : PlaceDetailAction()
    data class ClickFavorite(val like: Boolean) : PlaceDetailAction()
    data class ClickCourse(val courseId: String) : PlaceDetailAction()
}
