package org.romeo.layer_domain.entity.ad

import org.romeo.layer_domain.entity.list.ListItem
import org.romeo.layer_domain.app_state.AppStateEntity

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
): ListItem<Ad>, AppStateEntity
