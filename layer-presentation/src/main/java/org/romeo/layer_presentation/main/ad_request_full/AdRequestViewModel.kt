package org.romeo.layer_presentation.main.ad_request_full

import org.romeo.layer_domain.use_cases.GetAdUserUseCase
import org.romeo.layer_presentation.core.main.BaseAdFullViewModel
import org.romeo.layer_presentation.core.navigation.AppNavigator

class AdRequestViewModel(
    override val navigator: AppNavigator,
    useCase: GetAdUserUseCase,
    adId: String
) : BaseAdFullViewModel(navigator, useCase, adId) {

    override fun onBottomBtnPressed() {
        //TODO("Not yet implemented")
    }

}