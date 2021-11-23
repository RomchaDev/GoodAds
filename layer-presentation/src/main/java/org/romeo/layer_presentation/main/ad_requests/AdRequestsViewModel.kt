package org.romeo.layer_presentation.main.ad_requests

import org.romeo.layer_domain.entity.ad.Ads
import org.romeo.layer_domain.repository_bounderies.AdsRepository
import org.romeo.layer_presentation.core.app_state.AppState
import org.romeo.layer_presentation.core.main.BaseViewModel
import org.romeo.layer_presentation.core.navigation.AppNavigator

class AdRequestsViewModel(
    override val navigator: AppNavigator,
    private val adsRepository: AdsRepository
) : BaseViewModel<Ads>() {

    override fun onViewInit() {
        runAsync {
            mStateLiveData.postValue(AppState.Success(adsRepository.getMyAdRequests()))
        }
    }



}