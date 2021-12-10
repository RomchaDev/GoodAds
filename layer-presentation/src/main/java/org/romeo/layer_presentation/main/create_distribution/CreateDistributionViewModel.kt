package org.romeo.layer_presentation.main.create_distribution

import org.romeo.layer_domain.app_state.AppStateEntity
import org.romeo.layer_domain.entity.distribution.Distribution
import org.romeo.layer_domain.repository_bounderies.DistributionRepository
import org.romeo.layer_presentation.core.app_state.AppState
import org.romeo.layer_presentation.core.main.BaseViewModel
import org.romeo.layer_presentation.core.navigation.AppNavigator

class CreateDistributionViewModel(
    override val navigator: AppNavigator,
    private val distributionRepository: DistributionRepository
) : BaseViewModel<DismissAppStateEntity>() {

    fun onCreatePressed(priceForOneAd: Int, advertisersNumber: Int) {
        runAsync {
            distributionRepository.createDistribution(
                Distribution(priceForOneAd, advertisersNumber)
            )
        }.invokeOnCompletion {
            dismiss()
        }
    }

    fun onCancelPressed() {
        dismiss()
    }

    private fun dismiss() {
        mStateLiveData.postValue(AppState.Success(DismissAppStateEntity))
    }
}