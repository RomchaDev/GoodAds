package org.romeo.layer_presentation.main.guest_login

import android.os.Bundle
import android.view.View
import org.romeo.layer_domain.entity.user.User
import org.romeo.layer_presentation.R
import org.romeo.layer_presentation.core.main.BaseFragment
import org.romeo.layer_presentation.databinding.FragmentGuestLoginBinding
import org.koin.android.viewmodel.ext.android.viewModel

class GuestLoginFragment :
    BaseFragment<FragmentGuestLoginBinding, User, GuestLoginViewModel>(R.layout.fragment_guest_login) {

    override val viewModel: GuestLoginViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogin.setOnClickListener {
            viewModel.onLoginPressed()
        }
    }

}