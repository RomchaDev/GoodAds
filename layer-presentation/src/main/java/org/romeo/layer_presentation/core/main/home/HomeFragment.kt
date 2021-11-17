package org.romeo.layer_presentation.core.main.home

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.android.viewmodel.ext.android.viewModel
import org.romeo.layer_domain.entity.list.items.UserAdsListItem
import org.romeo.layer_presentation.R
import org.romeo.layer_presentation.core.list.MainListAdapter
import org.romeo.layer_presentation.core.main.BaseFragment
import org.romeo.layer_presentation.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewState, HomeViewModel>(R.layout.fragment_home) {

    override val viewModel: HomeViewModel by viewModel()
    private lateinit var listAdapter: MainListAdapter<UserAdsListItem>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listAdapter = MainListAdapter(
            mapOf(
                UserAdsListItem.AD_VIEW_TYPE to R.layout.item_ad,
                UserAdsListItem.USER_VIEW_TYPE to R.layout.layout_user
            ), null
        )

        binding.myAdsRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.myAdsRecycler.adapter = listAdapter
    }


    override fun renderSuccess(data: HomeViewState) {
        listAdapter.submitList(data.stateList)
        super.renderSuccess(data)
    }

}