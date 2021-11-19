package org.romeo.layer_presentation.main.choose_ad_screen

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.android.viewmodel.ext.android.viewModel
import org.romeo.layer_domain.entity.ad.Ad
import org.romeo.layer_domain.entity.ad.Ads
import org.romeo.layer_domain.entity.list.ListItem
import org.romeo.layer_presentation.R
import org.romeo.layer_presentation.core.list.MainListAdapter
import org.romeo.layer_presentation.core.main.BaseFragment
import org.romeo.layer_presentation.databinding.FragmentChooseAdBinding

class ChooseAdFragment :
    BaseFragment<FragmentChooseAdBinding, Ads, ChooseAdViewModel>(
        R.layout.fragment_choose_ad
    ) {

    override val viewModel: ChooseAdViewModel by viewModel()
    private var adapter: MainListAdapter<Ad>? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
    }

    override fun renderSuccess(data: Ads) {
        super.renderSuccess(data)

        adapter?.submitList(data.adsList)
    }

    private fun initList() {
        adapter = MainListAdapter(
            mapOf(ListItem.DEFAULT_ITEM_LAYOUT_KEY to R.layout.item_ad )
        ) { itemBinding, ad ->
            itemBinding.root.setOnClickListener {
                viewModel.onAdChosen(ad)
            }
        }

        binding.adsList.layoutManager =
            LinearLayoutManager(requireContext())

        binding.adsList.adapter = adapter
    }
}