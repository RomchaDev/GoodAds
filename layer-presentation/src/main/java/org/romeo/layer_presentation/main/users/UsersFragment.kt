package org.romeo.layer_presentation.main.users

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.romeo.layer_presentation.R
import org.romeo.layer_presentation.core.main.BaseFragment
import org.romeo.layer_presentation.databinding.FragmentUsersBinding
import org.koin.android.viewmodel.ext.android.viewModel
import org.romeo.layer_domain.entity.list.ListItem
import org.romeo.layer_domain.entity.list.items.UserAdsListItem
import org.romeo.layer_domain.entity.user.User
import org.romeo.layer_presentation.core.list.MainListAdapter
import org.romeo.layer_presentation.databinding.LayoutUserBinding

class UsersFragment :
    BaseFragment<FragmentUsersBinding, UsersViewState, UsersViewModel>(R.layout.fragment_users) {

    override val viewModel: UsersViewModel by viewModel()
    private lateinit var listAdapter: MainListAdapter<User>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listAdapter = MainListAdapter(
            mapOf(ListItem.DEFAULT_ITEM_LAYOUT_KEY to R.layout.layout_user)
        ) { binding, user ->
            if (binding is LayoutUserBinding) {
                binding.data = UserAdsListItem.UserListItem(user)
                binding.etPostPrice.isFocusable = false
                binding.etStoryPrice.isFocusable = false
            }

            binding.root.setOnClickListener {
                viewModel.onUserClicked(user.id)
            }
        }

        binding.adsRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.adsRecycler.adapter = listAdapter
    }

    override fun renderSuccess(data: UsersViewState) {
        listAdapter.submitList(data.stateList)
        super.renderSuccess(data)
    }

    override fun showMessage(message: String) {
        MaterialAlertDialogBuilder(requireContext())
            .setMessage(message)
            .setNeutralButton(R.string.ok, null)
            .show()
    }
}