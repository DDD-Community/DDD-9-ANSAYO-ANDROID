package com.ddd.ansayo.domain.usecase.favorite

import com.ddd.ansayo.core_model.common.Response
import com.ddd.ansayo.core_model.place.FavoritePlacesEntity
import com.ddd.ansayo.domain.repository.PlaceRepository
import javax.inject.Inject

class GetFavoritePlacesUseCase @Inject constructor(
    private val placeRepository: PlaceRepository
) {

    suspend operator fun invoke() : Response<FavoritePlacesEntity.Response> {
        return placeRepository.getFavoritePlaces()
    }
}
