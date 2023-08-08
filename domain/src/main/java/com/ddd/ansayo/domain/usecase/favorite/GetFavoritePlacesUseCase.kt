package com.ddd.ansayo.domain.usecase.favorite

import com.ddd.ansayo.core_model.common.Response
import com.ddd.ansayo.core_model.place.Place
import javax.inject.Inject

class GetFavoritePlacesUseCase @Inject constructor() {

    suspend operator fun invoke() : Response<List<Place>> {
        TODO()
    }
}
