package org.romeo.layer_presentation.main.home

import android.os.Bundle
import org.romeo.layer_domain.entity.ad.CreateEditAdEntity
import org.romeo.layer_domain.entity.list.items.UserAdsListItem
import org.romeo.layer_domain.repository_bounderies.AdsRepository
import org.romeo.layer_domain.repository_bounderies.UserRepository
import org.romeo.layer_domain.use_cases.GetUserAdsUseCase
import org.romeo.layer_presentation.core.app_state.AppState
import org.romeo.layer_presentation.core.main.BaseViewModel
import org.romeo.layer_presentation.core.navigation.*
import org.romeo.layer_presentation.core.navigation.commands.interfaces.AnyToAdFullCommand
import org.romeo.layer_presentation.core.navigation.commands.interfaces.AnyToCreateEditAdCommand
import org.romeo.layer_presentation.core.navigation.commands.interfaces.HomeToGuestLoginCommand

class HomeViewModel(
    override val navigator: AppNavigator,
    private val getUserAdsUseCase: GetUserAdsUseCase,
    private val userRepository: UserRepository,
    private val adsRepository: AdsRepository,
    private val adFullCommand: AnyToAdFullCommand,
    private val createEditCommand: AnyToCreateEditAdCommand,
    private val homeToGuestLoginCommand: HomeToGuestLoginCommand
) : BaseViewModel<HomeViewState>() {

    private var ads = mutableListOf<UserAdsListItem>()

    override fun onViewInit() {
        runAsync {
            val token = userRepository.getToken()
            //val token = null

            token?.let {
                ads = getUserAdsUseCase.execute() as MutableList<UserAdsListItem>
                mSharedFlow.emit(AppState.Success(HomeViewState(ads)))
            } ?: runOnMainThread { navigator.navigate(homeToGuestLoginCommand) }
        }

        navigator.subscribeToResult(object : NavigationResultListener<CreateEditAdEntity> {
            override fun onNavigationResult(result: CreateEditAdEntity?) {
                result?.let {
                    runAsync {
                        adsRepository.createEditAd(result)
                    }
                }
            }

        }, CREATE_AD_OUT)
    }

    fun onPriceChanged(postPrice: Int, storyPrice: Int) {
        runAsync {
            userRepository.changePrices(postPrice, storyPrice)
        }
    }

    fun onAdClicked(id: String) {
        navigator.navigate(
            adFullCommand,
            Bundle().apply { putString(AD_FULL_KEY, id) }
        )
    }

    fun onDeleteAdClicked(id: String) {
        runAsync {
            val adsNew = ads.toMutableList()
            adsNew.removeIf { ad ->
                if (ad is UserAdsListItem.AdListItem) ad.ad.id == id
                else false
            }

            mSharedFlow.emit(AppState.Success(HomeViewState(adsNew)))
            ads = adsNew
            adsRepository.deleteAd(id)
        }
    }

    fun onEditAdClicked(id: String) {
        runAsync {
            val ad = adsRepository.getAd(id)
            navigator.navigate(
                createEditCommand,
                Bundle().apply { putParcelable(CREATE_EDIT_AD_IN, ad) }
            )
        }
    }

    override fun handleError(error: Throwable) {
        if (error !is IllegalStateException)
            super.handleError(error)
    }

    fun onCreateAdPressed() {
        navigator.navigate(createEditCommand)
    }
}