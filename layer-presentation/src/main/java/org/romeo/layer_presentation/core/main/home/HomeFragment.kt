package org.romeo.layer_presentation.core.main.home

import org.koin.android.viewmodel.ext.android.viewModel
import org.romeo.layer_domain.entity.user.User
import org.romeo.layer_presentation.R
import org.romeo.layer_presentation.core.main.BaseFragment
import org.romeo.layer_presentation.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding, User, HomeViewModel>(R.layout.fragment_home) {

    override val viewModel: HomeViewModel by viewModel()

}