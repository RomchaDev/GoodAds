package org.romeo.layer_presentation.main.home

import org.romeo.layer_domain.use_cases.GetUserAdsUseCase
import org.romeo.layer_presentation.core.main.BaseViewModel
import org.romeo.layer_presentation.core.navigation.AppNavigator

class HomeViewModel(
    override val navigator: AppNavigator,
    private val getUserAdsUseCase: GetUserAdsUseCase
) : BaseViewModel<HomeViewState>() {

    override fun onViewInit() {
        runAsync {
            getUserAdsUseCase.execute()
        }
    }
}