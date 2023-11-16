package com.ddd.ansayo.domain.model.search.keyword

import com.ddd.ansayo.core_model.search.RecentKeyword

sealed class SearchAction {
    object EnteredScreen : SearchAction()
    data class ClickKeyword(val keyword: String): SearchAction()
    data class ClickKeywordDelete(val recentKeyword: RecentKeyword): SearchAction()

}
