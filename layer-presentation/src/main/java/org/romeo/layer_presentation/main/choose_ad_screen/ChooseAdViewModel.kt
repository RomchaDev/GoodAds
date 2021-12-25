package org.romeo.layer_presentation.main.choose_ad_screen

import org.romeo.layer_domain.entity.ad.Ad
import org.romeo.layer_domain.entity.ad.Ads
import org.romeo.layer_domain.repository_bounderies.AdsRepository
import org.romeo.layer_presentation.core.app_state.AppState
import org.romeo.layer_presentation.core.main.BaseViewModel
import org.romeo.layer_presentation.core.navigation.CHOOSE_AD_KEY
import org.romeo.layer_presentation.core.navigation.AppNavigator

class ChooseAdViewModel(
    override val navigator: AppNavigator,
    private val adsRepository: AdsRepository
) : BaseViewModel<Ads>() {

    override fun onViewInit() {
        runAsync {
            val ads = adsRepository.getMyAds()
            mSharedFlow.emit(AppState.Success(ads))
        }
    }

    fun onAdChosen(ad: Ad) {
        navigator.setResult(CHOOSE_AD_KEY, ad)
        navigator.back()
    }
}