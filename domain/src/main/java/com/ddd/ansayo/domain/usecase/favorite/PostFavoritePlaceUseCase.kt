package com.ddd.ansayo.domain.usecase.favorite

import com.ddd.ansayo.core_model.common.Response
import com.ddd.ansayo.core_model.place.FavoritePlacesEntity
import com.ddd.ansayo.domain.repository.PlaceRepository
import com.ddd.ansayo.domain.util.toResponse
import javax.inject.Inject

class PostFavoritePlaceUseCase @Inject constructor(
    private val placeRepository: PlaceRepository
) {

    suspend operator fun invoke(id: String): Response<Unit> {
        return placeRepository.postFavoritePlace(FavoritePlacesEntity.PostRequest(id)).toResponse()
    }
}
