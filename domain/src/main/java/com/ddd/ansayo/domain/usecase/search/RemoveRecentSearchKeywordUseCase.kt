package com.ddd.ansayo.domain.usecase.search

import com.ddd.ansayo.core_model.search.RecentKeyword
import com.ddd.ansayo.domain.repository.SearchRepository
import javax.inject.Inject

class RemoveRecentSearchKeywordUseCase @Inject constructor(
    private val searchRepository: SearchRepository
){
    operator fun invoke(recentKeyword: RecentKeyword) =
        searchRepository.removeLatestSearchKeyword(recentKeyword)

}