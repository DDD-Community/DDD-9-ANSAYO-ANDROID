package com.ddd.ansayo.domain.repository

import com.ddd.ansayo.core_model.common.Response
import com.ddd.ansayo.core_model.search.SearchPlaceEntity

interface PlaceSearchRepository {
    suspend fun getPlaceCourse(): Response<SearchPlaceEntity.Response>
}