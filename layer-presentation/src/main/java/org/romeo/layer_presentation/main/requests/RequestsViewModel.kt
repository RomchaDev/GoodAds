package org.romeo.layer_presentation.main.requests

import android.os.Bundle
import org.romeo.layer_domain.entity.list.items.UserAdsListItem
import org.romeo.layer_domain.repository_bounderies.AdsRepository
import org.romeo.layer_domain.repository_bounderies.UserRepository
import org.romeo.layer_presentation.core.app_state.AppState
import org.romeo.layer_presentation.core.main.BaseViewModel
import org.romeo.layer_presentation.core.navigation.AD_FULL_KEY
import org.romeo.layer_presentation.core.navigation.AppNavigator
import org.romeo.layer_presentation.core.navigation.commands.interfaces.AnyToAdFullCommand
import org.romeo.layer_presentation.core.navigation.commands.interfaces.RequestsToAdRequestCommand

class RequestsViewModel(
    override val navigator: AppNavigator,
    private val adsRepository: AdsRepository,
    private val userRepository: UserRepository,
    private val adRequestCommand: RequestsToAdRequestCommand
) : BaseViewModel<RequestsViewState>() {

    override fun onViewInit() {
        getAdRequests()
    }

    fun getAdRequests() {
        runAsync {
            val list: MutableList<UserAdsListItem.AdListItem> = mutableListOf()

            adsRepository.getMyAdRequests().adsList.forEach {
                list.add(UserAdsListItem.AdListItem(it))
            }

            mStateLiveData.postValue(AppState.Success(RequestsViewState(list)))
        }
    }

    fun getUserRequests() {
        runAsync {
            val list: MutableList<UserAdsListItem.UserListItem> = mutableListOf()

            userRepository.getMyUserRequests().forEach {
                list.add(UserAdsListItem.UserListItem(it))
            }

            mStateLiveData.postValue(AppState.Success(RequestsViewState(list)))
        }
    }

    fun onAdClicked(id: String) {
        navigator.navigate(
            adRequestCommand,
            Bundle().apply { putString(AD_FULL_KEY, id) }
        )
    }

}