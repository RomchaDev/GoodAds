package org.romeo.layer_presentation.main.guest_login

import org.romeo.layer_domain.entity.user.User
import org.romeo.layer_domain.repository_bounderies.UserRepository
import org.romeo.layer_presentation.core.main.BaseViewModel
import org.romeo.layer_presentation.core.navigation.AppNavigator
import org.romeo.layer_presentation.core.navigation.commands.interfaces.AnyToHomeCommand
import org.romeo.layer_presentation.core.navigation.commands.interfaces.AnyToLoginCommand
import org.romeo.layer_presentation.core.navigation.commands.interfaces.LoginToHomeCommand

class GuestLoginViewModel(
    override val navigator: AppNavigator,
    private val userRepository: UserRepository,
    private val anyToLoginCommand: AnyToLoginCommand,
    private val anyToHomeCommand: AnyToHomeCommand
) : BaseViewModel<User>() {

    fun onLoginPressed() {
        navigator.navigate(anyToLoginCommand)
    }

    fun homeNavigated() {
        runAsync {
            val token = userRepository.getToken()
            token?.let { runOnMainThread { navigator.navigate(anyToHomeCommand) } }
        }
    }

}