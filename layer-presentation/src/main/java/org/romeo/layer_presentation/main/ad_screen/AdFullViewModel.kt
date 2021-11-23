package org.romeo.layer_presentation.main.ad_screen

import org.romeo.layer_domain.entity.AdUser
import org.romeo.layer_domain.use_cases.GetAdUserUseCase
import org.romeo.layer_presentation.core.app_state.AppState
import org.romeo.layer_presentation.core.main.BaseViewModel
import org.romeo.layer_presentation.core.navigation.AppNavigator

class AdFullViewModel(
    override val navigator: AppNavigator,
    private val useCase: GetAdUserUseCase,
    private val adId: String
) : BaseViewModel<AdUser>() {

    override fun onViewInit() {
        runAsync {
            val adUser = useCase.execute(adId)
            mStateLiveData.postValue(AppState.Success(adUser))
        }
    }

    fun onAcceptPressed() {

    }

    fun onDistributionPressed() {

    }
}