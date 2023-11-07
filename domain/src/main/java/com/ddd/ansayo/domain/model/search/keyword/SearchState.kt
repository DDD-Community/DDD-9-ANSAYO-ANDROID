package com.ddd.ansayo.domain.model.search.keyword

import com.ddd.ansayo.core_model.search.RecentKeyword

data class SearchState(
    val keyword: RecentKeyword?,
    val recentKeyword: List<RecentKeyword>
) {
    companion object {
        val EMPTY = SearchState(
            keyword = null,
            recentKeyword = listOf()
        )
    }
}
