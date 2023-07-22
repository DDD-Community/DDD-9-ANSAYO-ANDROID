package com.ddd.ansayo.domain.model.common

sealed class Response<out T> {
    data class Success<T>(val data: T) : Response<T>()
    data class Fail(val message: String) : Response<Nothing>()
}
