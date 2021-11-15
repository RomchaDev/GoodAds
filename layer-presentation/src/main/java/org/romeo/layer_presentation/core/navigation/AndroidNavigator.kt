package org.romeo.layer_presentation.core.navigation

import androidx.fragment.app.FragmentActivity
import androidx.navigation.Navigation

//action_createEditAdFragment_to_fragment_home
//action_fragment_home_to_createEditAdFragment

class AndroidNavigator(
    private val navHostActivity: FragmentActivity,
    private val hostFragmentId: Int
) : INavigator {

    override fun navigate(command: NavigationCommand) {
        command.execute(Navigation.findNavController(navHostActivity, hostFragmentId))
    }
}