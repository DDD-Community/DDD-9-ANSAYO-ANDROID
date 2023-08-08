package com.ddd.ansayo.image

import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.ddd.ansayo.R
import com.ddd.ansayo.base.BaseActivity
import com.ddd.ansayo.databinding.ActivityImageViewerBinding
import com.ddd.ansayo.presentation.viewmodel.Constant

class ImageViewerActivity :
    BaseActivity<ActivityImageViewerBinding>(ActivityImageViewerBinding::inflate) {

    private val images: List<String> by lazy {
        intent.getStringArrayExtra(Constant.IMAGES).orEmpty().toList()
    }
    private val pageChangeListener = object : OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            binding.tvPageCount.text = getString(R.string.count_per_total_count, position + 1, images.size)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
    }

    private fun initView() {
        binding.vpImage.apply {
            adapter = ImageViewerAdapter(images)
            registerOnPageChangeCallback(pageChangeListener)
        }
    }

    override fun onDestroy() {
        binding.vpImage.unregisterOnPageChangeCallback(pageChangeListener)
        super.onDestroy()
    }
}
