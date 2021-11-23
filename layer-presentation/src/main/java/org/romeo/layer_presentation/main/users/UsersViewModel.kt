package org.romeo.layer_presentation.main.users

import org.romeo.layer_domain.entity.ad.Ad
import org.romeo.layer_domain.repository_bounderies.UserRepository
import org.romeo.layer_presentation.core.app_state.AppState
import org.romeo.layer_presentation.core.main.BaseViewModel
import org.romeo.layer_presentation.core.navigation.AD_KEY
import org.romeo.layer_presentation.core.navigation.AppNavigator
import org.romeo.layer_presentation.core.navigation.NavigationResultListener
import org.romeo.layer_presentation.core.navigation.commands.interfaces.AnyToChoseAdCommand

class UsersViewModel(
    override val navigator: AppNavigator,
    private val userRepository: UserRepository,
    private val usersToChoseAdCommand: AnyToChoseAdCommand
) : BaseViewModel<UsersViewState>() {

    override fun onViewInit() {
        runAsync {
            mStateLiveData.postValue(AppState.Success(UsersViewState(userRepository.getUsers())))
        }
    }

    fun onUserClicked(userId: String) {
        var chosenAd: Ad? = null

        navigator.navigate(usersToChoseAdCommand)
        navigator.subscribeToResult(object : NavigationResultListener<Ad> {
            override fun onNavigationResult(result: Ad?) {
                chosenAd = result
            }
        }, AD_KEY)

        runAsync {
            chosenAd?.let { userRepository.sendMyAd(userId, it.id) }
        }

    }

}