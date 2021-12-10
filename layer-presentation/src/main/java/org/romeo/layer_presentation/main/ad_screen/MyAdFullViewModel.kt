package org.romeo.layer_presentation.main.ad_screen

import org.romeo.layer_domain.use_cases.GetAdUserUseCase
import org.romeo.layer_presentation.core.main.BaseAdFullViewModel
import org.romeo.layer_presentation.core.navigation.AppNavigator
import org.romeo.layer_presentation.core.navigation.commands.interfaces.AdFullToDistributionCommand

class MyAdFullViewModel(
    override val navigator: AppNavigator,
    private val distributionCommand: AdFullToDistributionCommand,
    useCase: GetAdUserUseCase,
    adId: String
) : BaseAdFullViewModel(navigator, useCase, adId) {

    override fun onBottomBtnPressed() {
        navigator.navigate(distributionCommand)
    }

}