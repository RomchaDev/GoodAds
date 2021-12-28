package org.romeo.layer_presentation.main.user_request_full

import org.romeo.layer_domain.entity.request_full.RequestFull
import org.romeo.layer_domain.repository_bounderies.RequestsRepository
import org.romeo.layer_presentation.core.app_state.AppState
import org.romeo.layer_presentation.core.main.BaseViewModel
import org.romeo.layer_presentation.core.navigation.AppNavigator

class UserRequestViewModel(
    override val navigator: AppNavigator,
    private val requestsRepository: RequestsRepository,
    private val userId: String
) : BaseViewModel<RequestFull>() {

    override fun onViewInit() {
        runAsync {
            mSharedFlow.emit(AppState.Success(requestsRepository.getRequest(userId)))
        }
    }

    fun onAcceptPressed() {

    }

    fun onDeclinePressed() {
        runAsync {
            requestsRepository.declineRequest(userId)
        }

        navigator.back()
    }

}