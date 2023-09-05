package com.ddd.ansayo.domain.model.home


sealed class HomeMutation {

    sealed class Mutation : HomeMutation() {

    }

    sealed class SideEffect : HomeMutation() {
        data class NaviToCourseDetail(val courseId: String) : SideEffect()
        data class ShowSnackBar(val message: String) : SideEffect()
    }
}
