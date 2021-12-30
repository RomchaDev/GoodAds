package org.romeo.layer_presentation.main.guest_login

import org.romeo.layer_domain.entity.user.User
import org.romeo.layer_domain.repository_bounderies.UserRepository
import org.romeo.layer_presentation.core.main.BaseViewModel
import org.romeo.layer_presentation.core.navigation.AppNavigator

class GuestLoginViewModel(
    override val navigator: AppNavigator,
    private val userRepository: UserRepository,
    private val anyToLoginCommand: AnyToLoginCommand
) : BaseViewModel<User>() {

    fun notifyHomeNavigated() {
        runAsync {
            val token = userRepository.getToken()

            runOnMainThread {
                navigator.navigate(anyToLoginCommand)
                //token ?: navigator.navigate(anyToLoginCommand)
            }
        }
    }

}