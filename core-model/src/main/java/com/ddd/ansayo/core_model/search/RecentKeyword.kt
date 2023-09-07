package com.ddd.ansayo.core_model.search

data class RecentKeyword(
    val keyword: String,
    var onClickedDelete: ((RecentKeyword) -> Unit)? = null

)
