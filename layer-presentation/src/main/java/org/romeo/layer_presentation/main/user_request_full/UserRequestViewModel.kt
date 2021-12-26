package org.romeo.layer_presentation.main.user_request_full

import org.romeo.layer_domain.entity.AdUser
import org.romeo.layer_domain.repository_bounderies.AdsRepository
import org.romeo.layer_domain.repository_bounderies.UserRepository
import org.romeo.layer_presentation.core.app_state.AppState
import org.romeo.layer_presentation.core.main.BaseViewModel
import org.romeo.layer_presentation.core.navigation.AppNavigator

class UserRequestViewModel(
    override val navigator: AppNavigator,
    private val adsRepository: AdsRepository,
    private val userRepository: UserRepository,
    private val userId: String
) : BaseViewModel<AdUser>() {

    override fun onViewInit() {
        runAsync {
            mSharedFlow.emit(AppState.Success(adsRepository.getUserRequest(userId)))
        }
    }

    fun onAcceptPressed() {

    }

    fun onDeclinePressed() {
        runAsync {
            userRepository.declineUserRequest(userId)
        }

        navigator.back()
    }

}