package org.romeo.layer_presentation.main.ad_list_item_full

import org.romeo.layer_domain.repository_bounderies.AdsRepository
import org.romeo.layer_domain.repository_bounderies.UserRepository
import org.romeo.layer_domain.use_cases.GetAdUserUseCase
import org.romeo.layer_presentation.core.main.BaseAdFullViewModel
import org.romeo.layer_presentation.core.navigation.AppNavigator

class AdListItemFullViewModel(
    override val navigator: AppNavigator,
    private val adsRepository: AdsRepository,
    useCase: GetAdUserUseCase,
    adId: String
) : BaseAdFullViewModel(navigator, useCase, adId) {

    override fun onBottomBtnPressed() {
        runAsync {
            adsRepository.advertiseOtherAd(adId)
        }
    }

}