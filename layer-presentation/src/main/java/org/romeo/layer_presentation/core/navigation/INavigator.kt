package org.romeo.layer_presentation.core.navigation

//action_createEditAdFragment_to_fragment_home
//action_fragment_home_to_createEditAdFragment

interface INavigator {
    fun navigate(command: NavigationCommand)
}