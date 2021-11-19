package org.romeo.layer_presentation.main.home

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.android.viewmodel.ext.android.viewModel
import org.romeo.layer_domain.entity.list.items.UserAdsListItem
import org.romeo.layer_presentation.R
import org.romeo.layer_presentation.core.list.MainListAdapter
import org.romeo.layer_presentation.core.main.BaseFragment
import org.romeo.layer_presentation.databinding.FragmentHomeBinding
import org.romeo.layer_presentation.databinding.ItemAdBinding
import org.romeo.layer_presentation.databinding.LayoutUserBinding

class HomeFragment :
    BaseFragment<FragmentHomeBinding, HomeViewState, HomeViewModel>(R.layout.fragment_home) {

    override val viewModel: HomeViewModel by viewModel()
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

                binding.root.setOnClickListener {
                    viewModel.onAdClicked(item.ad.id)
                }

                binding.root.setOnLongClickListener { view ->
                    val menu = PopupMenu(requireContext(), view)
                    menu.inflate(R.menu.delete_edit_menu)

                    menu.setOnMenuItemClickListener {
                        when (it.itemId) {
                            R.id.delete -> {
                                viewModel.onDeleteAdClicked(item.ad.id)
                                true
                            }

                            R.id.edit -> {
                                viewModel.onEditAdClicked(item.ad.id)
                                true
                            }

                            else -> false
                        }
                    }

                    menu.show()
                    return@setOnLongClickListener true
                }
            }

            if (item is UserAdsListItem.UserListItem && binding is LayoutUserBinding) {
                binding.etPostPrice.setOnFocusChangeListener { _, hasFocus ->
                    onFocusChange(binding, hasFocus)
                }

                binding.etStoryPrice.setOnFocusChangeListener { _, hasFocus ->
                    onFocusChange(binding, hasFocus)
                }
            }

        }

        binding.myAdsRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.myAdsRecycler.adapter = listAdapter
    }


    override fun renderSuccess(data: HomeViewState) {
        listAdapter.submitList(data.stateList)
        super.renderSuccess(data)
    }

    private fun onFocusChange(binding: LayoutUserBinding, hasFocus: Boolean) {
        if (!hasFocus) viewModel.onPriceChanged(
            binding.etPostPrice.text.toString().toInt(),
            binding.etStoryPrice.text.toString().toInt()
        )
    }

}