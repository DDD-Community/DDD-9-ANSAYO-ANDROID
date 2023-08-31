package com.ddd.ansayo.domain.handler.search

import com.ddd.ansayo.core_model.common.Response
import com.ddd.ansayo.domain.model.search.SearchPlaceAction
import com.ddd.ansayo.domain.model.search.SearchPlaceMutation
import com.ddd.ansayo.domain.model.search.SearchPlaceState
import com.ddd.ansayo.domain.usecase.place.GetPlacesUseCase
import com.ddd.ansayo.domain.usecase.place.GetSearchPlaceUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class SearchPlaceMutationHandler @Inject constructor(
    private val getSearchPlaceUseCase: GetSearchPlaceUseCase
) {
    suspend fun mutate(
        state: SearchPlaceState,
        action: SearchPlaceAction
    ): Flow<SearchPlaceMutation> {
        return flow {
            when(action) {
                is SearchPlaceAction.SearchKeyword -> {
                    when(val result = getSearchPlaceUseCase(action.searchKeyword)) {
                        is Response.Fail -> {
                            emit(SearchPlaceMutation.SideEffect.ShowSnackBar(result.message))
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
                SearchPlaceAction.ClickSeachBar -> {
                    flowOf(SearchPlaceMutation.SideEffect.SerachScreen)
                }
            }
        }
    }
}