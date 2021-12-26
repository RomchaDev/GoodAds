package org.romeo.layer_presentation.main.requests

import android.os.Bundle
import org.romeo.layer_domain.entity.list.items.UserAdsListItem
import org.romeo.layer_domain.repository_bounderies.AdsRepository
import org.romeo.layer_domain.repository_bounderies.UserRepository
import org.romeo.layer_presentation.core.app_state.AppState
import org.romeo.layer_presentation.core.main.BaseViewModel
import org.romeo.layer_presentation.core.navigation.AD_FULL_KEY
import org.romeo.layer_presentation.core.navigation.AppNavigator
import org.romeo.layer_presentation.core.navigation.USER_FULL_KEY
import org.romeo.layer_presentation.core.navigation.commands.interfaces.RequestsToAdRequestCommand
import org.romeo.layer_presentation.core.navigation.commands.interfaces.RequestsToUserRequestCommand

class RequestsViewModel(
    override val navigator: AppNavigator,
    private val adsRepository: AdsRepository,
    private val userRepository: UserRepository,
    private val adRequestCommand: RequestsToAdRequestCommand,
    private val userRequestCommand: RequestsToUserRequestCommand
) : BaseViewModel<RequestsViewState>() {

    var items = mutableListOf<UserAdsListItem>()

    override fun onViewInit() {
        getAdRequests()
    }

    fun getAdRequests() {
        runAsync {
            val list: MutableList<UserAdsListItem> = mutableListOf()

            adsRepository.getAdRequests().adsList.forEach {
                list.add(UserAdsListItem.AdListItem(it))
            }

            updateList(list)

            items = list
        }
    }

    fun getUserRequests() {
        runAsync {
            val list: MutableList<UserAdsListItem> = mutableListOf()

            userRepository.getUserRequests().users.forEach {
                list.add(UserAdsListItem.UserListItem(it))
            }

            updateList(list)

            items = list
        }
    }

    private fun updateList(list: MutableList<UserAdsListItem>) {
        runAsync {
            mSharedFlow.emit(AppState.Success(RequestsViewState(list)))
        }
    }

    fun onAdClicked(id: String) {
        navigator.navigate(
            adRequestCommand,
            Bundle().apply { putString(AD_FULL_KEY, id) }
        )
    }

    fun declineUser(userId: String) {
        runAsync {
            userRepository.declineUserRequest(userId)
            val itemsNew = items.toMutableList()
            itemsNew.removeIf { (it as UserAdsListItem.UserListItem).user.id == userId }
            updateList(itemsNew)
            items = itemsNew
        }
    }

    fun declineAd(adId: String) {
        runAsync {
            adsRepository.declineAd(adId)
            val itemsNew = items.toMutableList()
            itemsNew.removeIf { (it as UserAdsListItem.AdListItem).ad.id == adId }
            updateList(itemsNew)
            items = itemsNew
        }
    }

    fun onUserClicked(id: String) {
        navigator.navigate(
            userRequestCommand,
            Bundle().apply { putString(USER_FULL_KEY, id) }
        )
    }
}