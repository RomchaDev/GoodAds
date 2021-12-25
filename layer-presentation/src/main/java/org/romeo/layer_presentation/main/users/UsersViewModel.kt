package org.romeo.layer_presentation.main.users

import org.romeo.layer_domain.entity.ad.Ad
import org.romeo.layer_domain.repository_bounderies.AdsRepository
import org.romeo.layer_domain.repository_bounderies.UserRepository
import org.romeo.layer_presentation.core.app_state.AppState

import org.romeo.layer_presentation.core.main.BaseViewModel
import org.romeo.layer_presentation.core.navigation.AppNavigator
import org.romeo.layer_presentation.core.navigation.CHOOSE_AD_KEY
import org.romeo.layer_presentation.core.navigation.NavigationResultListener
import org.romeo.layer_presentation.core.navigation.commands.interfaces.AnyToChoseAdCommand
import org.romeo.layer_presentation.core.view.REQUEST_SUCCESSFULLY_SENT_MESSAGE

class UsersViewModel(
    override val navigator: AppNavigator,
    private val userRepository: UserRepository,
    private val adsRepository: AdsRepository,
    private val usersToChoseAdCommand: AnyToChoseAdCommand
) : BaseViewModel<UsersViewState>() {

    override fun onViewInit() {
        runAsync {
            mSharedFlow.emit(AppState.Success(UsersViewState(userRepository.getUsers())))
        }

        navigator.subscribeToResult(object : NavigationResultListener<Ad> {
            override fun onNavigationResult(result: Ad?) {
                chosenAd = result
                result?.id?.let { adId ->
                    userIdChosen?.let { uid ->
                        runAsync {
                            adsRepository.advertiseMyAd(uid, adId)
                        }
                    }
                }
            }
        }, CHOOSE_AD_KEY)

        chosenAd?.let {
            runOnMainThread {
                mSharedFlow.emit(AppState.Message(REQUEST_SUCCESSFULLY_SENT_MESSAGE))
                chosenAd = null
            }
        }
    }

    fun onUserClicked(userId: String) {
        navigator.navigate(usersToChoseAdCommand)
        userIdChosen = userId
    }

    companion object {
        private var userIdChosen: String? = null
        private var chosenAd: Ad? = null
    }
}