package com.ddd.ansayo.main.home

import android.os.Bundle
import android.view.View
import com.ddd.ansayo.base.BaseFragment
import com.ddd.ansayo.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        collectState()
        collectSideEffect()
    }
    private fun initView() {}
    private fun collectState() {}
    private fun collectSideEffect() {}

}
