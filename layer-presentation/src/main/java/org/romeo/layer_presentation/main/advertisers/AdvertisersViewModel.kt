package org.romeo.layer_presentation.main.advertisers

import org.romeo.layer_domain.entity.ad.Ad
import org.romeo.layer_domain.entity.user.Users
import org.romeo.layer_domain.repository_bounderies.AdsRepository
import org.romeo.layer_domain.repository_bounderies.RequestsRepository
import org.romeo.layer_domain.repository_bounderies.UserRepository
import org.romeo.layer_presentation.core.app_state.AppState

import org.romeo.layer_presentation.core.main.BaseViewModel
import org.romeo.layer_presentation.core.navigation.AppNavigator
import org.romeo.layer_presentation.core.navigation.CHOOSE_AD_KEY
import org.romeo.layer_presentation.core.navigation.NavigationResultListener
import org.romeo.layer_presentation.core.navigation.commands.interfaces.AnyToChoseAdCommand
import org.romeo.layer_presentation.core.view.REQUEST_SUCCESSFULLY_SENT_MESSAGE

class AdvertisersViewModel(
    override val navigator: AppNavigator,
    private val userRepository: UserRepository,
    private val requestsRepository: RequestsRepository,
    private val usersToChoseAdCommand: AnyToChoseAdCommand
) : BaseViewModel<Users>() {

    override fun onViewInit() {
        runAsync {
            mSharedFlow.emit(
                AppState.Success(
                    userRepository.getAdvertisers(
                        DEFAULT_START_NUM,
                        DEFAULT_END_NUM
                    )
                )
            )
        }

        navigator.subscribeToResult(object : NavigationResultListener<Ad> {
            override fun onNavigationResult(result: Ad?) {
                chosenAd = result
                result?.id?.let { adId ->
                    userIdChosen?.let { uid ->
                        runAsync {
                            requestsRepository.createRequest(uid, adId)
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
        private const val DEFAULT_START_NUM = 0
        private const val DEFAULT_END_NUM = 9
    }
}