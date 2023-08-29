package com.ddd.ansayo.domain.usecase.badge

import com.ddd.ansayo.core_model.badge.BadgeEntity
import com.ddd.ansayo.core_model.common.Response
import javax.inject.Inject

class GetBadgeUseCase @Inject constructor(
){
    suspend operator fun invoke() : Response<BadgeEntity> {
        TODO()
    }
}