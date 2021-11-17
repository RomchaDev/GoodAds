package org.romeo.layer_presentation.core.main.login

import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import org.koin.android.viewmodel.ext.android.viewModel
import org.romeo.layer_domain.entity.user.User
import org.romeo.layer_presentation.R
import org.romeo.layer_presentation.core.main.BaseFragment
import org.romeo.layer_presentation.databinding.FragmentLoginBinding

class LoginFragment :
    BaseFragment<FragmentLoginBinding, User, LoginViewModel>(R.layout.fragment_login) {

    override val viewModel: LoginViewModel by viewModel()
    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        binding.btnLogin.setOnClickListener {
            viewModel.onLoginPressed(
                binding.etLogin.text.toString(),
                binding.etPassword.text.toString()
            )
        }
    }

}