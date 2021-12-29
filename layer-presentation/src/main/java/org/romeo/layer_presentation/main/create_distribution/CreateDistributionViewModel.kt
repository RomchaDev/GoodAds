package org.romeo.layer_presentation.main.create_distribution

import org.romeo.layer_domain.entity.distribution.Distribution
import org.romeo.layer_domain.repository_bounderies.DistributionRepository
import org.romeo.layer_presentation.core.app_state.AppState
import org.romeo.layer_presentation.core.main.BaseViewModel
import org.romeo.layer_presentation.core.navigation.AppNavigator

class CreateDistributionViewModel(
    override val navigator: AppNavigator,
    private val distributionRepository: DistributionRepository,
    private val adId: String
) : BaseViewModel<DismissAppStateEntity>() {

    fun onCreatePressed(priceForOneAd: Int, advertisersNumber: Int) {
        runAsync {
            distributionRepository.createDistribution(
                Distribution(adId, priceForOneAd, advertisersNumber)
            )
        }.invokeOnCompletion {
            dismiss()
        }
    }

    fun onCancelPressed() {
        dismiss()
    }

    private fun dismiss() {
        runAsync {
            mSharedFlow.emit(AppState.Success(DismissAppStateEntity))
        }
    }
}