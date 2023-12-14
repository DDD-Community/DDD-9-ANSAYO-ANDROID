package com.ddd.ansayo.domain.model.search.keyword

import com.ddd.ansayo.core_model.search.RecentKeyword
import com.ddd.ansayo.domain.model.course.info.CourseInfoMutation

sealed class SearchMutation {
    sealed class Mutation: SearchMutation() {
        data class UpdateRecentKeyword(val keyword: List<RecentKeyword>): Mutation()
        data class DeleteRecentKeyword(val keyword: List<RecentKeyword>): Mutation()
    }
    sealed class SideEffect: SearchMutation() {
        object BackScreen: SideEffect()
        data class DeleteKeyword(val keyword: List<RecentKeyword>): SideEffect()
        data class StartSearchResultScreen(val keyword: String): SideEffect()
        data class ShowSnackBar(val message: String) : SideEffect()

    }
}
