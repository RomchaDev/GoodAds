package org.romeo.layer_domain.entity.user

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginResponse(
    val token: String,
    val user: User
): Parcelable
