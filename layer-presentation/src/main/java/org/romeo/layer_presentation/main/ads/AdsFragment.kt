package org.romeo.layer_presentation.main.ads

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.android.viewmodel.ext.android.viewModel
import org.romeo.layer_domain.entity.ad.Ad
import org.romeo.layer_domain.entity.ad.Ads
import org.romeo.layer_domain.entity.list.ListItem
import org.romeo.layer_presentation.R
import org.romeo.layer_presentation.core.list.MainListAdapter
import org.romeo.layer_presentation.core.main.BaseFragment
import org.romeo.layer_presentation.databinding.FragmentAdsBinding
import org.romeo.layer_presentation.databinding.ItemAdBinding

class AdsFragment :
    BaseFragment<FragmentAdsBinding, Ads, AdsViewModel>(R.layout.fragment_ads) {

    override val viewModel: AdsViewModel by viewModel()
    private lateinit var listAdapter: MainListAdapter<Ad>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listAdapter = MainListAdapter(
            mapOf(ListItem.DEFAULT_ITEM_LAYOUT_KEY to R.layout.item_ad)
        ) { binding, ad ->
            if (binding is ItemAdBinding) {
                binding.peopleImage.isVisible = true
                binding.peopleText.isVisible = true
                binding.priceText.isVisible = true
                binding.data = ad
            }

            binding.root.setOnClickListener {
                ad.id?.let { id -> viewModel.onAdClicked(id) }
            }
        }

        binding.adsRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.adsRecycler.adapter = listAdapter
    }

    override fun renderSuccess(data: Ads) {
        listAdapter.submitList(data.adsList)
        super.renderSuccess(data)
    }

}