package com.ddd.ansayo.domain.usecase.place

import com.ddd.ansayo.core_model.common.Response
import com.ddd.ansayo.core_model.place.SearchPlaceEntity
import com.ddd.ansayo.domain.repository.PlaceRepository
import javax.inject.Inject

class GetSearchPlaceUseCase @Inject constructor(
    private val placeRepository: PlaceRepository
) {
    suspend operator fun invoke(query: String): Response<SearchPlaceEntity> {
        return placeRepository.getSearchPlaces(query)
    }
}