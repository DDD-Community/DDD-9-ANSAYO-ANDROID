package com.ddd.ansayo.domain.model.home

import com.ddd.ansayo.core_model.place.Place

sealed class HomeMutation {

    sealed class Mutation : HomeMutation() {

    }

    sealed class SideEffect : HomeMutation() {
        data class NaviToCourseDetail(val id: String) : SideEffect()
        data class ShowSnackBar(val message: String) : SideEffect()
    }
}
