package org.romeo.layer_domain.entity.ad_user

import org.romeo.layer_domain.app_state.AppStateEntity
import org.romeo.layer_domain.entity.ad.Ad
import org.romeo.layer_domain.entity.user.User

data class AdUser(
    val ad: Ad,
    val user: User
): AppStateEntity