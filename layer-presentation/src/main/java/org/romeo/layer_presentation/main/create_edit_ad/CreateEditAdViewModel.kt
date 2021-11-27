package org.romeo.layer_presentation.main.create_edit_ad

import android.view.View
import org.romeo.layer_domain.app_state.AppStateEntity
import org.romeo.layer_domain.entity.ad.Ad
import org.romeo.layer_domain.entity.ad.CreateEditAdEntity
import org.romeo.layer_presentation.core.app_state.AppState
import org.romeo.layer_presentation.core.main.BaseViewModel
import org.romeo.layer_presentation.core.navigation.AppNavigator
import org.romeo.layer_presentation.core.navigation.CREATE_AD_OUT

class CreateEditAdViewModel(
    override val navigator: AppNavigator,
    ad: Ad?
) : BaseViewModel<AppStateEntity>() {
    val ad = ad ?: Ad.emptyAd()
    var images: List<ByteArray>? = null

    fun onCreateAdClicked(view: View) {
        images?.let { images ->
            navigator.setResult(CREATE_AD_OUT, CreateEditAdEntity(ad, images))
            navigator.back()
        } ?: mStateLiveData.postValue(
            AppState.Error(
                RuntimeException("There should been at least one image chosen")
            )
        )
    }
}