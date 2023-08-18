package com.ddd.ansayo.core_design.util

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.ddd.ansayo.core_design.R
import com.ddd.ansayo.core_design.databinding.LayoutRatingViewBinding

class RatingView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attributeSet, defStyleAttr) {

    private val binding = LayoutRatingViewBinding.inflate(LayoutInflater.from(context), this, true)
    var isEmphasized: Boolean = false
        set(value) {
            field = value
            val color = if (value) {
                ContextCompat.getColor(context, R.color.orange_point)
            } else {
                ContextCompat.getColor(context, R.color.N40)
            }
            binding.ivStar.setColorFilter(color)
            binding.tvCurrentRating.setTextColor(color)
        }

    var currentRating: Float = 0.0f
        set(value) {
            field = value
            binding.tvCurrentRating.text = value.toString()
        }

    var reviewCount: Int = 0
        set(value) {
            field = value
            binding.tvReviewCount.text = context.getString(R.string.review_count, field)
        }

    init {
        val attrs = context.obtainStyledAttributes(attributeSet, R.styleable.RatingView)
        isEmphasized = attrs.getBoolean(R.styleable.RatingView_emphasize, false)
        currentRating = attrs.getFloat(R.styleable.RatingView_currentRating, 0f)
        reviewCount = attrs.getInt(R.styleable.RatingView_reviewCount, 0)
        attrs.recycle()
    }
}
