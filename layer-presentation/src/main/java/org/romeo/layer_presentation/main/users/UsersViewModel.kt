package org.romeo.layer_presentation.main.users

import org.romeo.layer_domain.entity.ad.Ad
import org.romeo.layer_domain.repository_bounderies.UserRepository
import org.romeo.layer_presentation.core.app_state.AppState

import org.romeo.layer_presentation.core.main.BaseViewModel
import org.romeo.layer_presentation.core.navigation.AppNavigator
import org.romeo.layer_presentation.core.navigation.CHOOSE_AD_KEY
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

        navigator.subscribeToResult(object : NavigationResultListener<Ad> {
            override fun onNavigationResult(result: Ad?) {
                runAsync {
                    result?.id?.let { adId ->
                        userIdChosen?.let { uid ->
                            userRepository.applyMyAd(uid, adId)
                        }
                    }
                }
            }
        }, CHOOSE_AD_KEY)
    }

    fun onUserClicked(userId: String) {
        navigator.navigate(usersToChoseAdCommand)
        userIdChosen = userId
    }

    companion object {
        private var userIdChosen: String? = null
    }
}