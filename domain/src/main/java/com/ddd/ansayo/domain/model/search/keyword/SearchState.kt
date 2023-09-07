package com.ddd.ansayo.domain.model.search.keyword

import com.ddd.ansayo.core_model.search.RecentKeyword

data class SearchState(
    val keyword: List<RecentKeyword>
) {
    companion object {
        val EMPTY = SearchState(
            keyword = listOf()
        )
    }
}
