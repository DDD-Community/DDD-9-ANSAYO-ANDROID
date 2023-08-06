package com.ddd.ansayo.domain.usecase.design

import java.awt.Dialog

interface DialogUseCase {
    fun showDefaultButtonDialog(
        contents: String,
        image: String,
        title: String,
        contentsDate: String,
        buttonInfo: Pair<String, (() -> Any)?>
    )


    fun showBadgeButtonDialog(
        contents: String,
        negativeButtonInfo: Pair<String, (() -> Any)?>,
        positiveButtonInfo: Triple<String, Int, (() -> Any)?>
    )
}