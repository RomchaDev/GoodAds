package org.romeo.layer_presentation.main.requests

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.PopupMenu
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.LinearLayoutManager
import org.romeo.layer_presentation.R
import org.romeo.layer_presentation.core.main.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel
import org.romeo.layer_domain.entity.list.items.UserAdsListItem
import org.romeo.layer_presentation.core.list.MainListAdapter
import org.romeo.layer_presentation.core.view.active_inactive_view.ListenerState
import org.romeo.layer_presentation.databinding.FragmentAdvertisingRequestsBinding
import org.romeo.layer_presentation.databinding.ItemAdBinding
import org.romeo.layer_presentation.databinding.LayoutUserBinding

class AdvertisingRequestsFragment :
    BaseFragment<FragmentAdvertisingRequestsBinding, AdvertisingRequestsViewState, AdvertisingRequestsViewModel>(R.layout.fragment_advertising_requests),
    ListenerState {

    override val viewModel: AdvertisingRequestsViewModel by viewModel()
    private lateinit var listAdapter: MainListAdapter<AdvertisingRequestListItem>
    private val callback: OnBackPressedCallback =
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                //do nothing
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listAdapter = MainListAdapter(
            mapOf(
                UserAdsListItem.AD_VIEW_TYPE to R.layout.item_ad,
                UserAdsListItem.USER_VIEW_TYPE to R.layout.layout_user
            )
        ) { binding, item ->

            initItemMenu(binding, item)

            if (item is AdvertisingRequestListItem.AdRequestListItem && binding is ItemAdBinding) {
                binding.data = item.ad

                binding.root.setOnClickListener {
                    viewModel.onAdClicked(item.requestId)
                }

            } else if (item is AdvertisingRequestListItem.UserRequestListItem && binding is LayoutUserBinding) {
                binding.data = UserAdsListItem.UserListItem(item.user)
                binding.etPostPrice.isFocusable = false
                binding.etStoryPrice.isFocusable = false

                binding.root.setOnClickListener {
                    viewModel.onUserClicked(item.requestId)
                }
            }
        }

        binding.adsRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.adsRecycler.adapter = listAdapter

        binding.activeInactiveButton.listenerState = this

        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    private fun initItemMenu(
        binding: ViewDataBinding,
        item: AdvertisingRequestListItem
    ) {
        binding.root.setOnLongClickListener { view ->
            val menu = PopupMenu(requireContext(), view)
            menu.inflate(R.menu.decline_menu)

            menu.setOnMenuItemClickListener { menuItem ->
                if (menuItem.itemId == R.id.delete) {
                    if (item is AdvertisingRequestListItem.AdRequestListItem && binding is ItemAdBinding)
                        item.ad.id?.let { adId -> viewModel.declineRequest(adId) }
                    else if (item is AdvertisingRequestListItem.UserRequestListItem && binding is LayoutUserBinding) {
                        viewModel.declineRequest(item.user.id)
                    }
                    true
                } else false
            }

            menu.show()
            true
        }
    }

    override fun renderSuccess(data: AdvertisingRequestsViewState) {
        listAdapter.submitList(data.stateList)
        super.renderSuccess(data)
    }

    override fun onStateChanged(state: Boolean) {
        if (state) viewModel.getUserRequests()
        else viewModel.getAdRequests()
    }

}