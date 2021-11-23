package org.romeo.layer_presentation.main.ad_requests

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import org.romeo.layer_domain.entity.ad.Ads
import org.romeo.layer_presentation.R
import org.romeo.layer_presentation.core.main.BaseFragment
import org.romeo.layer_presentation.databinding.FragmentAdRequestsBinding
import org.koin.android.viewmodel.ext.android.viewModel
import org.romeo.layer_domain.entity.ad.Ad
import org.romeo.layer_domain.entity.list.ListItem
import org.romeo.layer_presentation.core.list.MainListAdapter
import org.romeo.layer_presentation.databinding.ItemAdBinding

class AdRequestsFragment :
    BaseFragment<FragmentAdRequestsBinding, Ads, AdRequestsViewModel>(R.layout.fragment_ad_requests) {

    override val viewModel: AdRequestsViewModel by viewModel()
    private lateinit var listAdapter: MainListAdapter<Ad>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listAdapter = MainListAdapter(
            mapOf(ListItem.DEFAULT_ITEM_LAYOUT_KEY to R.layout.item_ad)
        ) { binding, ad ->
            if (binding is ItemAdBinding) {
                binding.data = ad
            }
        }

        binding.myUsersRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.myUsersRecycler.adapter = listAdapter
    }

    override fun renderSuccess(data: Ads) {
        listAdapter.submitList(data.adsList)
        super.renderSuccess(data)
    }

}