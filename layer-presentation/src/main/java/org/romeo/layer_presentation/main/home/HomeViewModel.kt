package org.romeo.layer_presentation.main.home

import org.romeo.layer_domain.repository_bounderies.UserRepository
import org.romeo.layer_domain.use_cases.GetUserAdsUseCase
import org.romeo.layer_presentation.core.app_state.AppState
import org.romeo.layer_presentation.core.main.BaseViewModel
import org.romeo.layer_presentation.core.navigation.AppNavigator
import org.romeo.layer_presentation.core.navigation.NavigationCommand

class HomeViewModel(
    override val navigator: AppNavigator,
    private val getUserAdsUseCase: GetUserAdsUseCase,
    private val repository: UserRepository,
    private val homeToAdFullCommand: NavigationCommand
) : BaseViewModel<HomeViewState>() {

    override fun onViewInit() {
        runAsync {
            mStateLiveData.postValue(AppState.Success(HomeViewState(getUserAdsUseCase.execute())))
        }
    }

    fun onPriceChanged(postPrice: Int, storyPrice: Int) {
        runAsync {
            repository.changePrices(postPrice, storyPrice)
        }
    }

    fun onAdClicked(id: String) {
        navigator.navigate(homeToAdFullCommand)
    }

}