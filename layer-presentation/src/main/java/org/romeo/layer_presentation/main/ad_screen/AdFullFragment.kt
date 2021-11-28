package org.romeo.layer_presentation.main.ad_screen

import android.os.Bundle
import android.view.View
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import org.romeo.layer_domain.entity.AdUser
import org.romeo.layer_presentation.R
import org.romeo.layer_presentation.core.main.BaseFragment
import org.romeo.layer_presentation.core.navigation.AD_FULL_KEY
import org.romeo.layer_presentation.databinding.FragmentAdBinding

class AdFullFragment :
    BaseFragment<FragmentAdBinding, AdUser, AdFullViewModel>(R.layout.fragment_ad) {

    override val viewModel: AdFullViewModel by viewModel {
        parametersOf(arguments?.getString(AD_FULL_KEY))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        initButtons()
    }

    private fun initButtons() {
        binding.bottomBtn.setOnClickListener {
            viewModel.onDistributionPressed()
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