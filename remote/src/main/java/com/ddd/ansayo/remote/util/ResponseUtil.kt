package com.ddd.ansayo.remote.util

import com.ddd.ansayo.core_model.common.Response
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.getOrThrow

inline fun <reified T> ApiResponse<T>.toResponse(): Response<T> {
    return runCatching {
        this.getOrThrow()
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
