package org.romeo.layer_presentation.core.main

import org.romeo.layer_domain.entity.ad_user.AdUser
import org.romeo.layer_domain.use_cases.GetAdUserUseCase
import org.romeo.layer_presentation.core.app_state.AppState
import org.romeo.layer_presentation.core.navigation.AppNavigator

abstract class BaseAdFullViewModel (
    override val navigator: AppNavigator,
    private val useCase: GetAdUserUseCase,
    protected val adId: String
) : BaseViewModel<AdUser>() {

    override fun onViewInit() {
        runAsync {
            val adUser = useCase.execute(adId)
            mSharedFlow.emit(AppState.Success(adUser))
        }
    }

    abstract fun onBottomBtnPressed()

}