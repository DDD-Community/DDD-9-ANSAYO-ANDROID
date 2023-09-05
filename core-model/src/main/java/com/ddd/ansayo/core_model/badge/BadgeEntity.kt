package com.ddd.ansayo.core_model.badge

sealed class BadgeEntity {

    data class Response(
        val badge: List<Badge>?
    ) : BadgeEntity()
}
