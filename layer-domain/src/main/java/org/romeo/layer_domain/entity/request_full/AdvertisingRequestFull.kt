package org.romeo.layer_domain.entity.request_full

import org.romeo.layer_domain.app_state.AppStateEntity
import org.romeo.layer_domain.entity.ad.Ad
import org.romeo.layer_domain.entity.user.User

data class AdvertisingRequestFull(
    val requestId: String,
    val ad: Ad,
    val user: User
): AppStateEntity