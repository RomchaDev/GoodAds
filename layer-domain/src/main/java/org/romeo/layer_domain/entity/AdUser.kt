package org.romeo.layer_domain.entity

import org.romeo.layer_domain.entity.ad.Ad
import org.romeo.layer_domain.entity.user.User

data class AdUser(
    val ad: Ad,
    val user: User
)