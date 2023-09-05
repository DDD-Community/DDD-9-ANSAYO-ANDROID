package com.ddd.ansayo.domain.usecase.badge

import com.ddd.ansayo.core_model.badge.BadgeEntity
import com.ddd.ansayo.core_model.common.Response
import com.ddd.ansayo.domain.repository.BadgeRepository
import javax.inject.Inject

class GetSearchableBadgeUseCase @Inject constructor(
    private val badgeRepository: BadgeRepository
){
    suspend operator fun invoke() : Response<BadgeEntity.Response> {
        return badgeRepository.getSearchableBadge()
    }
}