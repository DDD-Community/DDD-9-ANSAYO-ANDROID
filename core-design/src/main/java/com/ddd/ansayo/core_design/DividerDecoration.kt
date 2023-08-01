package com.ddd.ansayo.core_design

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class DividerDecoration(
    context: Context,
    margin: Int
) : RecyclerView.ItemDecoration() {

    private val divider: Drawable? = ContextCompat.getDrawable(context, R.drawable.divider)
    private val marginDp = (margin * context.resources.displayMetrics.density).toInt()

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.left = marginDp
        outRect.right = marginDp
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        divider?.let {
            val left = parent.paddingLeft + marginDp
            val right = parent.width - parent.paddingRight - marginDp

            val childCount = parent.childCount
            for (i in 0 until childCount - 1) {
                val child = parent.getChildAt(i)
                val params = child.layoutParams as RecyclerView.LayoutParams
                val top = child.bottom + params.bottomMargin
                val bottom = top + it.intrinsicHeight

                it.setBounds(left, top, right, bottom)
                it.draw(c)
            }
        }
    }
}
