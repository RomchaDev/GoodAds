package org.romeo.layer_domain.entity.ad

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import org.romeo.layer_domain.entity.list.ListItem
import org.romeo.layer_domain.app_state.AppStateEntity
import org.romeo.layer_domain.entity.list.Content
import org.romeo.layer_domain.entity.list.ListItemId

@Parcelize
data class Ad(
    @ListItemId val id: String,
    @Content val title: String,
    @Content val description: String,
    @Content val type: AdType,
    @Content val imageUrls: List<String>,
    val userId: String,
): ListItem<Ad>, AppStateEntity, Parcelable
