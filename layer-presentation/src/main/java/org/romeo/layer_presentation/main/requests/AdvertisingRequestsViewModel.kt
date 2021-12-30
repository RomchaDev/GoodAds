package org.romeo.layer_presentation.main.requests

import android.os.Bundle
import org.romeo.layer_domain.repository_bounderies.AdvertisingRequestsRepository
import org.romeo.layer_presentation.core.app_state.AppState
import org.romeo.layer_presentation.core.main.BaseViewModel
import org.romeo.layer_presentation.core.navigation.AD_FULL_KEY
import org.romeo.layer_presentation.core.navigation.AppNavigator
import org.romeo.layer_presentation.core.navigation.USER_REQUEST_FULL_KEY
import org.romeo.layer_presentation.core.navigation.commands.interfaces.RequestsToAdRequestCommand
import org.romeo.layer_presentation.core.navigation.commands.interfaces.RequestsToUserRequestCommand

class AdvertisingRequestsViewModel(
    override val navigator: AppNavigator,
    private val advertisingRequestsRepository: AdvertisingRequestsRepository,
    private val adRequestCommand: RequestsToAdRequestCommand,
    private val userRequestCommand: RequestsToUserRequestCommand
) : BaseViewModel<AdvertisingRequestsViewState>() {

    var items = mutableListOf<AdvertisingRequestListItem>()

    override fun onViewInit() {
        getAdRequests()
    }

    fun getAdRequests() {
        runAsync {
            val list: MutableList<AdvertisingRequestListItem> = mutableListOf()

            advertisingRequestsRepository.getAdvertisingRequestsFull().requests.forEach {
                list.add(AdvertisingRequestListItem.AdRequestListItem(it.requestId, it.ad))
            }

            updateList(list)

            items = list
        }
    }

    fun getUserRequests() {
        runAsync {
            val list: MutableList<AdvertisingRequestListItem> = mutableListOf()

            advertisingRequestsRepository.getAdvertisingRequestsFull().requests.forEach {
                list.add(AdvertisingRequestListItem.UserRequestListItem(it.requestId, it.user))
            }

            updateList(list)

            items = list
        }
    }

    private fun updateList(list: MutableList<AdvertisingRequestListItem>) {
        runAsync {
            mSharedFlow.emit(AppState.Success(AdvertisingRequestsViewState(list)))
        }
    }

    fun onAdClicked(requestId: String) {
        val adListItem = items.find { it.requestId == requestId }
                as AdvertisingRequestListItem.AdRequestListItem

        navigator.navigate(
            adRequestCommand,
            Bundle().apply { putString(AD_FULL_KEY, adListItem.ad.id) }
        )
    }

    fun onUserClicked(id: String) {
        navigator.navigate(
            userRequestCommand,
            Bundle().apply { putString(USER_REQUEST_FULL_KEY, id) }
        )
    }

    fun declineRequest(requestId: String) {
        runAsync {
            advertisingRequestsRepository.declineAdvertisingRequest(requestId)
            val itemsNew = items.toMutableList()
            itemsNew.removeIf { it.requestId == requestId }
            updateList(itemsNew)
            items = itemsNew
        }
    }
}