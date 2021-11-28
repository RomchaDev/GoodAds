package org.romeo.layer_presentation.main.create_edit_ad

import android.view.View
import org.romeo.layer_domain.entity.ad.Ad
import org.romeo.layer_domain.entity.ad.CreateEditAdEntity
import org.romeo.layer_presentation.core.app_state.AppState
import org.romeo.layer_presentation.core.main.BaseViewModel
import org.romeo.layer_presentation.core.navigation.AppNavigator
import org.romeo.layer_presentation.core.navigation.CREATE_AD_OUT

class CreateEditAdViewModel(
    override val navigator: AppNavigator,
    ad: Ad?
) : BaseViewModel<ImagesUrlsViewState>() {
    val ad = ad ?: Ad.emptyAd()
    var images: List<ByteArray>? = null

    override fun onViewInit() {
        runAsync {
            mStateLiveData.postValue(AppState.Success(ImagesUrlsViewState(ad.imageUrls)))
        }
    }

    fun onCreateAdClicked(view: View) {

        if (images != null || ad.imageUrls.isNotEmpty()) {
            navigator.setResult(CREATE_AD_OUT, CreateEditAdEntity(ad, images))
            navigator.back()
        } else {
            mStateLiveData.postValue(
                AppState.Error(
                    RuntimeException("There should been at least one image chosen")
                )
            )
        }
    }
}