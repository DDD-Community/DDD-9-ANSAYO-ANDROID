package com.ddd.ansayo.data.util

import com.ddd.ansayo.data.model.common.DataModel
import com.ddd.ansayo.domain.entity.common.EntityModel
import com.ddd.ansayo.domain.model.common.Response
import com.google.gson.Gson

inline fun <reified T : DataModel, reified R : EntityModel> T.toResponse(): Response<R> {
    return runCatching {
        this
    }.fold(
        onSuccess = {
            val gson = Gson()
            val dataJson = gson.toJson(T::class.java)
            val entity = gson.fromJson(dataJson, R::class.java)
            Response.Success(entity)
        },
        onFailure = {
            val errorMessage = it.message ?: "에러" // TODO("에러 메세지 문구 변경")
            Response.Fail(errorMessage)
        }
    )
}
