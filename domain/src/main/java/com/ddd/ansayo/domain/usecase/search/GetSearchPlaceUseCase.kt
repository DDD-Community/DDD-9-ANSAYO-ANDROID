package com.ddd.ansayo.domain.usecase.search

import com.ddd.ansayo.core_model.common.Response
import com.ddd.ansayo.core_model.search.SearchPlaceEntity
import com.ddd.ansayo.domain.repository.PlaceSearchRepository
import javax.inject.Inject

class GetSearchPlaceUseCase @Inject constructor(
    private val placeSearchRepository: PlaceSearchRepository
) {
    suspend operator fun invoke(): Response<SearchPlaceEntity.Response> {
        return placeSearchRepository.getPlaceCourse()
    }
}