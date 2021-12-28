package org.romeo.layer_presentation.main.user_request_full

import android.os.Bundle
import android.view.View
import org.romeo.layer_domain.entity.request_full.RequestFull
import org.romeo.layer_presentation.R
import org.romeo.layer_presentation.core.main.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import org.romeo.layer_domain.entity.list.items.UserAdsListItem
import org.romeo.layer_presentation.core.navigation.USER_FULL_KEY
import org.romeo.layer_presentation.databinding.FragmentRequestUserBinding

class UserRequestFragment :
    BaseFragment<FragmentRequestUserBinding, RequestFull, UserRequestViewModel>(R.layout.fragment_request_user) {

    override val viewModel: UserRequestViewModel by viewModel {
        parametersOf(arguments?.getString(USER_FULL_KEY))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnDecline.setOnClickListener {
            viewModel.onDeclinePressed()
        }
    }

    override fun renderSuccess(data: RequestFull) {
        super.renderSuccess(data)

        with(binding) {
            itemUser.data = UserAdsListItem.UserListItem(data.user)
            itemAd.data = data.ad
        }
    }

}