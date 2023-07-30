package com.ddd.ansayo.domain.util

import com.ddd.ansayo.core_model.common.Response

inline fun <reified T> T.toResponse(): Response<T> {
    return runCatching {
        this
    }.fold(
        onSuccess = {
            Response.Success(it)
        },
        onFailure = {
            val errorMessage = it.message ?: "에러" // TODO("에러 메세지 문구 변경")
            Response.Fail(errorMessage)
        }
    )
}
