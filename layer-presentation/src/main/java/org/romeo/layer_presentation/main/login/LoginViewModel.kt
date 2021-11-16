package org.romeo.layer_presentation.main.login

import org.romeo.layer_domain.entity.user.User
import org.romeo.layer_domain.repository_bounderies.UserRepository
import org.romeo.layer_presentation.core.main.BaseViewModel
import org.romeo.layer_presentation.core.navigation.AppNavigator
import org.romeo.layer_presentation.core.navigation.commands.impl.LoginToHomeCommandImpl
import org.romeo.layer_presentation.core.navigation.commands.interfaces.LoginToHomeCommand

class LoginViewModel(
    override val navigator: AppNavigator,
    private val userRepository: UserRepository,
    private val loginToHomeCommand: LoginToHomeCommand
) : BaseViewModel<User>() {

    fun onLoginPressed(login: String, password: String) = runAsync {
        userRepository.login(login, password)
        navigator.navigate(loginToHomeCommand)
    }
}