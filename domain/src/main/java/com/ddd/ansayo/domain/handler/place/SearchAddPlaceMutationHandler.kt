package com.ddd.ansayo.domain.handler.place

import com.ddd.ansayo.core_model.common.Response
import com.ddd.ansayo.domain.model.place.SearchAddPlaceAction
import com.ddd.ansayo.domain.model.place.SearchAddPlaceMutation
import com.ddd.ansayo.domain.model.place.SearchAddPlaceState
import com.ddd.ansayo.domain.usecase.place.GetPlacesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchAddPlaceMutationHandler @Inject constructor(
    private val getPlacesUseCase: GetPlacesUseCase
) {

    suspend fun mutate(
        state: SearchAddPlaceState,
        action: SearchAddPlaceAction
    ): Flow<SearchAddPlaceMutation> {
        return flow {
            when (action) {
                SearchAddPlaceAction.ClickBackButton -> {
                    emit(SearchAddPlaceMutation.SideEffect.Finish)
                }

                is SearchAddPlaceAction.ClickSeeDetail -> {
                    emit(SearchAddPlaceMutation.SideEffect.GoDetail(action.placeId))
                }

                is SearchAddPlaceAction.SearchKeyword -> {
                    emit(SearchAddPlaceMutation.SideEffect.ShowLoading)
                    when (val response = getPlacesUseCase(action.searchKeyword)) {
                        is Response.Success -> {
                            emit(SearchAddPlaceMutation.Mutation.UpdateSearchResult(response.data))
                        }

                        is Response.Fail -> {
                            emit(SearchAddPlaceMutation.SideEffect.ShowSnackBar(response.message))
                        }
                    }
                    emit(SearchAddPlaceMutation.SideEffect.HideLoading)
                }

                is SearchAddPlaceAction.ClickAddPlace -> {
                    val place = state.places.first { it.placeId == action.placeId }
                    emit(SearchAddPlaceMutation.SideEffect.SendPlaceInfo(place))
                }
            }
        }
    }
}
