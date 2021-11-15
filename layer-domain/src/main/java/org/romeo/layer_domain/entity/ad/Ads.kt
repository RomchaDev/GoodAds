package org.romeo.layer_domain.entity.ad

import org.romeo.model.view_state.AppStateEntity

data class Ads(
    val adsList: List<Ad>
) : AppStateEntity