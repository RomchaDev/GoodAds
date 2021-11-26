package org.romeo.layer_presentation.main.ad_requests

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import org.romeo.layer_presentation.R
import org.romeo.layer_presentation.core.main.BaseFragment
import org.romeo.layer_presentation.databinding.FragmentRequestsBinding
import org.koin.android.viewmodel.ext.android.viewModel
import org.romeo.layer_domain.entity.list.items.UserAdsListItem
import org.romeo.layer_presentation.core.list.MainListAdapter
import org.romeo.layer_presentation.core.view.active_inactive_view.ListenerState
import org.romeo.layer_presentation.databinding.ItemAdBinding
import org.romeo.layer_presentation.databinding.LayoutUserBinding

class RequestsFragment :
    BaseFragment<FragmentRequestsBinding, RequestsViewState, RequestsViewModel>(R.layout.fragment_requests),
    ListenerState {

    override val viewModel: RequestsViewModel by viewModel()
    private lateinit var listAdapter: MainListAdapter<UserAdsListItem>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listAdapter = MainListAdapter(
            mapOf(
                UserAdsListItem.AD_VIEW_TYPE to R.layout.item_ad,
                UserAdsListItem.USER_VIEW_TYPE to R.layout.layout_user
            )
        ) { binding, item ->
            if (item is UserAdsListItem.AdListItem && binding is ItemAdBinding) {
                binding.data = item.ad
            } else if (item is UserAdsListItem.UserListItem && binding is LayoutUserBinding) {
                binding.data = UserAdsListItem.UserListItem(item.user)
            }
        }

        binding.myUsersRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.myUsersRecycler.adapter = listAdapter

        binding.activeInactiveButton.listenerState = this
    }

    override fun renderSuccess(data: RequestsViewState) {
        listAdapter.submitList(data.stateList)
        super.renderSuccess(data)
    }

    override fun onStateChanged(state: Boolean) {
        if (state) viewModel.getUserRequests()
        else viewModel.getAdRequests()
    }

}