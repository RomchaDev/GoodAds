package org.romeo.layer_domain.entity.ad

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CreateEditAdEntity(
    val ad: Ad,
    private val images: List<ByteArray>?
) : Parcelable