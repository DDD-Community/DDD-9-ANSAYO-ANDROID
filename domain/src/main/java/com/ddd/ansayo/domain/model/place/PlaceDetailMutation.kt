package com.ddd.ansayo.domain.model.place

import com.ddd.ansayo.core_model.course.Course

sealed class PlaceDetailMutation {
    sealed class Reduce : PlaceDetailMutation() {
        data class UpdatePlaceInfo(
            val placeDetail: PlaceDetail,
            val courses: List<Course>
        ) : Reduce()

        data class UpdateFavoriteCount(
            val placeDetail: PlaceDetail
        ) : Reduce()
    }

    sealed class SideEffect : PlaceDetailMutation() {
        object Finish : SideEffect()
        data class NaviToCourseDetail(val courseId: String) : SideEffect()
        data class ShowSnackBar(val message: String) : SideEffect()
    }
}
