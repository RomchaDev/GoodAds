package org.romeo.layer_presentation.main.requests

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.PopupMenu
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

            binding.root.setOnLongClickListener { view ->
                val menu = PopupMenu(requireContext(), view)
                menu.inflate(R.menu.delete_menu)

                menu.setOnMenuItemClickListener { menuItem ->
                    if (menuItem.itemId == R.id.delete) {
                        if (item is UserAdsListItem.AdListItem && binding is ItemAdBinding)
                            item.ad.id?.let { adId -> viewModel.declineAd(adId) }
                        else if (item is UserAdsListItem.UserListItem && binding is LayoutUserBinding) {
                            viewModel.declineUser(item.user.id)
                        }
                        true
                    } else false
                }

                menu.show()
                true
            }

            if (item is UserAdsListItem.AdListItem && binding is ItemAdBinding) {
                binding.data = item.ad

                binding.root.setOnClickListener {
                    item.ad.id?.let { id ->
                        viewModel.onAdClicked(id)
                    }
                }

            } else if (item is UserAdsListItem.UserListItem && binding is LayoutUserBinding) {
                binding.data = UserAdsListItem.UserListItem(item.user)
                binding.etPostPrice.isFocusable = false
                binding.etStoryPrice.isFocusable = false

                binding.root.setOnClickListener {
                    viewModel.onUserClicked(item.user.id)
                }
            }
        }

        binding.adsRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.adsRecycler.adapter = listAdapter

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