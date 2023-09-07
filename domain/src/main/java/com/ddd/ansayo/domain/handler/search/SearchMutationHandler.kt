package com.ddd.ansayo.domain.handler.search

import com.ddd.ansayo.domain.model.search.keyword.SearchAction
import com.ddd.ansayo.domain.model.search.keyword.SearchMutation
import com.ddd.ansayo.domain.model.search.keyword.SearchState
import com.ddd.ansayo.domain.usecase.search.GetRecentSearchKeywordUseCase
import com.ddd.ansayo.domain.usecase.search.RemoveRecentSearchKeywordUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchMutationHandler @Inject constructor(
    private val getRecentSearchKeywordUseCase: GetRecentSearchKeywordUseCase,
    private val removeRecentSearchKeywordUseCase: RemoveRecentSearchKeywordUseCase
){
    suspend fun mutate(
        state: SearchState,
        action: SearchAction
    ): Flow<SearchMutation> {
        return flow {
            when(action) {
                is SearchAction.EnteredScreen -> {
                   val result = getRecentSearchKeywordUseCase.invoke()
                   emit(
                       SearchMutation.Mutation.UpdateRecentKeyword(
                           keyword = result
                       )

                   )
                }
                is SearchAction.ClickKeyword -> {

                }
                is  SearchAction.ClickKeywordDelete -> {
                    val result = removeRecentSearchKeywordUseCase.invoke(action.recentKeyword)
                    emit(
                        SearchMutation.Mutation.DeleteRecentKeyword(
                            keyword = action.recentKeyword
                        )

                    )
                }
            }
        }
    }
}