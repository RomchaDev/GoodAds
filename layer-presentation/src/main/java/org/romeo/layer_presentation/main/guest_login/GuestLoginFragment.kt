package org.romeo.layer_presentation.main.guest_login

import android.os.Bundle
import org.romeo.layer_domain.entity.user.User
import org.romeo.layer_presentation.R
import org.romeo.layer_presentation.core.main.BaseFragment
import org.romeo.layer_presentation.databinding.FragmentGuestLoginBinding
import org.koin.android.viewmodel.ext.android.viewModel
import org.romeo.layer_presentation.main.home.HomeFragment
import org.romeo.layer_presentation.main.login.LoginFragment

class GuestLoginFragment :
    BaseFragment<FragmentGuestLoginBinding, User, GuestLoginViewModel>(R.layout.fragment_guest_login) {

    override val viewModel: GuestLoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (viewModel.isLogined()) {
            viewModel.navigateHome()
        } else {
            childFragmentManager
                .beginTransaction()
                .replace(R.id.guest_login_fragment_container, LoginFragment())
                .commit()
        }
    }

}