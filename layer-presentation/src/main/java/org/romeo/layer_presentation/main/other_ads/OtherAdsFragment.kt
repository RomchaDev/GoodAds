package org.romeo.layer_presentation.main.other_ads

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
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

class OtherAdsFragment :
    BaseFragment<FragmentAdsBinding, Ads, OtherAdsViewModel>(R.layout.fragment_ads) {

    override val viewModel: OtherAdsViewModel by viewModel()
    private lateinit var listAdapter: MainListAdapter<Ad>
    private val callback: OnBackPressedCallback =
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                //do nothing
            }
        }

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

        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun renderSuccess(data: Ads) {
        listAdapter.submitList(data.adsList)
        super.renderSuccess(data)
    }

}