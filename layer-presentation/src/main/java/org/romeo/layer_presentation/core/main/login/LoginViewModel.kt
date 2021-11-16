package org.romeo.layer_presentation.core.main.login

import org.romeo.layer_data.data_sources.FakeApiDataSource
import org.romeo.layer_data.dto.LoginRequest
import org.romeo.layer_domain.entity.user.User
import org.romeo.layer_presentation.core.main.BaseViewModel
import org.romeo.layer_presentation.core.navigation.AppNavigator

class LoginViewModel(navigator: AppNavigator) : BaseViewModel<User>(navigator) {

    private val dataSource = FakeApiDataSource()

    fun login(login: String, password: String) = dataSource.login(LoginRequest(login, password))

}