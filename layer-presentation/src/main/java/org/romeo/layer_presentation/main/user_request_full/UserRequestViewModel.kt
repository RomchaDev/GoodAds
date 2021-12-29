package org.romeo.layer_presentation.main.user_request_full

import org.romeo.layer_domain.entity.request_full.AdvertisingRequestFull
import org.romeo.layer_domain.repository_bounderies.AdvertisingRequestsRepository
import org.romeo.layer_presentation.core.app_state.AppState
import org.romeo.layer_presentation.core.main.BaseViewModel
import org.romeo.layer_presentation.core.navigation.AppNavigator

class UserRequestViewModel(
    override val navigator: AppNavigator,
    private val advertisingRequestsRepository: AdvertisingRequestsRepository,
    private val requestId: String
) : BaseViewModel<AdvertisingRequestFull>() {

    override fun onViewInit() {
        runAsync {
            mSharedFlow.emit(AppState.Success(advertisingRequestsRepository.getAdvertisingRequest(requestId)))
        }
    }

    fun onAcceptPressed() {

    }

    fun onDeclinePressed() {
        runAsync {
            advertisingRequestsRepository.declineAdvertisingRequest(requestId)
        }

        navigator.back()
    }

}