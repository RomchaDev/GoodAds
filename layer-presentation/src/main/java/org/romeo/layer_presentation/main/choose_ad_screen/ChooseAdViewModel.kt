package org.romeo.layer_presentation.main.choose_ad_screen

import org.romeo.layer_domain.entity.ad.Ad
import org.romeo.layer_domain.entity.ad.Ads
import org.romeo.layer_domain.repository_bounderies.AdsRepository
import org.romeo.layer_domain.repository_bounderies.UserRepository
import org.romeo.layer_domain.use_cases.GetMyAdsUseCase
import org.romeo.layer_presentation.core.app_state.AppState
import org.romeo.layer_presentation.core.main.BaseViewModel
import org.romeo.layer_presentation.core.navigation.CHOOSE_AD_KEY
import org.romeo.layer_presentation.core.navigation.AppNavigator

class ChooseAdViewModel(
    override val navigator: AppNavigator,
    private val getMyAdsUseCase: GetMyAdsUseCase
) : BaseViewModel<Ads>() {

    override fun onViewInit() {
        runAsync {
            val ads = getMyAdsUseCase.execute(0, 9)
            mSharedFlow.emit(AppState.Success(ads))
        }
    }

    fun onAdChosen(ad: Ad) {
        navigator.setResult(CHOOSE_AD_KEY, ad)
        navigator.back()
    }
}