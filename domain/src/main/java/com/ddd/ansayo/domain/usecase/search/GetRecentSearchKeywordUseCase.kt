package com.ddd.ansayo.domain.usecase.search

import com.ddd.ansayo.domain.repository.SearchRepository
import javax.inject.Inject

class GetRecentSearchKeywordUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {
    operator fun invoke() = searchRepository.getRecentSearchKeyword()
}