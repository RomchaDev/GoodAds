package org.romeo.layer_presentation.main.guest_login

import org.romeo.layer_domain.entity.user.User
import org.romeo.layer_presentation.core.main.BaseViewModel
import org.romeo.layer_presentation.core.navigation.AppNavigator
import org.romeo.layer_presentation.core.navigation.commands.interfaces.GuestLoginToLoginCommand

class GuestLoginViewModel(
    override val navigator: AppNavigator,
    private val guestLoginToLoginCommand: GuestLoginToLoginCommand
) : BaseViewModel<User>() {

    fun onLoginPressed() {
        navigator.navigate(guestLoginToLoginCommand)
    }

}