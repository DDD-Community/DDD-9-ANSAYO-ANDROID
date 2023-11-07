package com.ddd.ansayo.data.repository

import com.ddd.ansayo.core_model.search.RecentKeyword
import com.ddd.ansayo.data.SearchedQueryStore
import com.ddd.ansayo.domain.repository.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val searchedQueryStore: SearchedQueryStore
): SearchRepository {
    override fun getRecentSearchKeyword(): List<RecentKeyword> =
        searchedQueryStore.getTexts().map { RecentKeyword(it) }

    override fun removeLatestSearchKeyword(recentKeyword: RecentKeyword) =
        searchedQueryStore.removeText(recentKeyword.keyword)
}