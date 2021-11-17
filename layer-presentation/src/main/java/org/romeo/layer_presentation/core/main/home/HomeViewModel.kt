package org.romeo.layer_presentation.core.main.home

import org.romeo.layer_domain.entity.user.User
import org.romeo.layer_presentation.core.main.BaseViewModel
import org.romeo.layer_presentation.core.navigation.AppNavigator

class HomeViewModel(override val navigator: AppNavigator) : BaseViewModel<User>() {
}