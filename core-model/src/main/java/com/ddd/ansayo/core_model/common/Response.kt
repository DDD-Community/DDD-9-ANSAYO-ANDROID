package com.ddd.ansayo.core_model.common

sealed class Response<out T> {
    data class Success<T>(val data: T) : Response<T>()
    data class Fail(val message: String) : Response<Nothing>()
}
