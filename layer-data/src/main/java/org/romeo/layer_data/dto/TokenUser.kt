package org.romeo.layer_data.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import org.romeo.layer_domain.entity.user.User

@Parcelize
data class TokenUser(
    val token: String,
    val user: User
): Parcelable
