package org.romeo.layer_presentation.core.main

import android.os.Bundle
import android.view.View
import org.romeo.layer_domain.entity.AdUser
import org.romeo.layer_domain.entity.request_full.RequestFull
import org.romeo.layer_presentation.R
import org.romeo.layer_presentation.databinding.FragmentAdBinding
import org.romeo.layer_presentation.main.ad_screen.AdViewPagerAdapter

abstract class BaseAdFullFragment :
    BaseFragment<FragmentAdBinding, AdUser, BaseAdFullViewModel>(R.layout.fragment_ad) {

    abstract val bottomButtonText: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()

        binding.bottomBtn.text = bottomButtonText

        binding.bottomBtn.setOnClickListener {
            viewModel.onBottomBtnPressed()
        }
    }

    private fun initAdapter() {
        with(binding) {
            vpImages.adapter = AdViewPagerAdapter()
            indicator.setViewPager(vpImages)
        }
    }

    override fun renderSuccess(data: AdUser) {
        super.renderSuccess(data)

        with(binding) {
            adUser = data
            (vpImages.adapter as AdViewPagerAdapter).images = data.ad.imageUrls
        }
    }
}