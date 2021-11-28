package org.romeo.layer_presentation.main.create_edit_ad

import org.romeo.layer_domain.app_state.AppStateEntity

data class ImagesUrlsViewState(
    val imageUrls: List<String>
) : AppStateEntity
