package com.ddd.ansayo.domain.usecase.place

import com.ddd.ansayo.core_model.common.Response
import com.ddd.ansayo.core_model.place.PlaceInfoEntity
import com.ddd.ansayo.domain.repository.PlaceRepository
import javax.inject.Inject

class GetPlaceInfoUseCase @Inject constructor(
    private val placeRepository: PlaceRepository
) {

    suspend operator fun invoke(placeId: String): Response<PlaceInfoEntity> {
        return placeRepository.getPlaceInfo(placeId, DEFAULT_COURSE_COUNT)
    }

    companion object {
        private const val DEFAULT_COURSE_COUNT = 3
    }
}
