package com.ddd.ansayo.data.repository.design

import android.content.Context
import com.ddd.ansayo.core_design.util.dialog.BadgeDialog
import com.ddd.ansayo.core_design.util.dialog.DefaultDialog
import com.ddd.ansayo.domain.usecase.design.DialogUseCase
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class DialogUseCaseImpl @Inject constructor(
    @ActivityContext private val context: Context
) : DialogUseCase {
    override fun showDefaultButtonDialog(
        contents: String,
        image: String,
        title: String,
        contentsDate: String,
        buttonInfo: Pair<String, (() -> Any)?>
    ) {
       BadgeDialog(context, image, title, contents, contentsDate, buttonInfo)
           .show()
    }

    override fun showBadgeButtonDialog(
        contents: String,
        negativeButtonInfo: Pair<String, (() -> Any)?>,
        positiveButtonInfo: Triple<String, Int, (() -> Any)?>
    ) {
        DefaultDialog(context, contents, negativeButtonInfo, positiveButtonInfo)
            .show()
    }

}