package org.romeo.layer_domain.entity.ad

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import org.romeo.layer_domain.entity.list.ListItem
import org.romeo.layer_domain.app_state.AppStateEntity

@Parcelize
data class Ad(
    val id: String,
    val userId: String,
    val title: String,
    val description: String,
    val type: AdType,
    val price: String,
    val freePlaces: Int,
    val imageUrls: List<String>,
    val isLiked: Boolean = false
): ListItem<Ad>, AppStateEntity, Parcelable
