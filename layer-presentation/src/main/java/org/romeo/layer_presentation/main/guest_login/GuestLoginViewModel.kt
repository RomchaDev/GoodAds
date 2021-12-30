package org.romeo.layer_presentation.main.guest_login

import org.romeo.layer_domain.entity.user.User
import org.romeo.layer_domain.repository_bounderies.UserRepository
import org.romeo.layer_presentation.core.main.BaseViewModel
import org.romeo.layer_presentation.core.navigation.AppNavigator
import org.romeo.layer_presentation.core.navigation.commands.interfaces.LoginToHomeCommand

class GuestLoginViewModel(
    override val navigator: AppNavigator,
    private val userRepository: UserRepository,
    private val loginToHomeCommand: LoginToHomeCommand
) : BaseViewModel<User>() {

    fun isLogined(): Boolean {
        var result = true
        runAsync {
            val token = userRepository.getToken()

            result = false
            //token ?: run { result = false }
        }
        return result
    }

    fun navigateHome() {
        navigator.navigate(loginToHomeCommand)
    }

}