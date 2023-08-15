package com.ddd.ansayo.domain.handler.search

import com.ddd.ansayo.core_model.common.Response
import com.ddd.ansayo.domain.model.search.SearchPlaceAction
import com.ddd.ansayo.domain.model.search.SearchPlaceMutation
import com.ddd.ansayo.domain.usecase.search.GetSearchPlaceUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class SearchPlaceMutationHandler @Inject constructor(
    private val getSearchPlaceUseCase: GetSearchPlaceUseCase
) {
    suspend fun mutate(
        state: SearchPlaceMutation,
        action: SearchPlaceAction
    ): Flow<SearchPlaceMutation> {
        return flow {
            when(action) {
                is SearchPlaceAction.InputPlaceSearchWord -> {
                    flowOf(
                        SearchPlaceMutation.Mutation.UpdateSearchWord(
                            word = action.text
                        )
                    )
                }
                is SearchPlaceAction.ClickSearch -> {
                    when(val result = getSearchPlaceUseCase()) {
                        is Response.Fail -> {

                        }
                        is Response.Success -> {
                            emit(
                                SearchPlaceMutation.Mutation.UpdatePlace(
                                    place = result.data.places.orEmpty()
                                )
                            )
                        }
                    }
                }
                is SearchPlaceAction.ClickPlaceList -> {
                    emit(SearchPlaceMutation.SideEffect.StartPlaceDetail(action.id))
                }
                is SearchPlaceAction.SelectSearchListTab -> {
                    flowOf(SearchPlaceMutation.SideEffect.NavToCourse)
                }
                is SearchPlaceAction.ClickBackButton -> {
                    flowOf(SearchPlaceMutation.SideEffect.BackScreen)
                }
            }
        }
    }
}