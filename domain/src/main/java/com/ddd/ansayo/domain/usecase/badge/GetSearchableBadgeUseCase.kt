package com.ddd.ansayo.domain.usecase.badge

import com.ddd.ansayo.core_model.badge.SearchableBadgeEntity
import com.ddd.ansayo.core_model.common.Response
import javax.inject.Inject

class GetSearchableBadgeUseCase @Inject constructor(
){
    suspend operator fun invoke() : Response<SearchableBadgeEntity> {
        TODO()
    }
}