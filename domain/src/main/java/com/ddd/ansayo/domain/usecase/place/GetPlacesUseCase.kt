package com.ddd.ansayo.domain.usecase.place

import com.ddd.ansayo.core_model.common.Response
import com.ddd.ansayo.core_model.place.Place
import com.ddd.ansayo.domain.repository.PlaceRepository
import com.ddd.ansayo.domain.util.toResponse
import javax.inject.Inject

class GetPlacesUseCase @Inject constructor(
    private val placeRepository: PlaceRepository
) {

    suspend operator fun invoke(query: String): Response<List<Place>> {
        return placeRepository.getPlaces(query).toResponse()
    }
}
