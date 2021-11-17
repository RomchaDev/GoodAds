package org.romeo.layer_domain.entity.list.items

import org.romeo.layer_domain.entity.ad.Ad
import org.romeo.layer_domain.entity.list.ListItem
import org.romeo.layer_domain.entity.user.User

sealed class UserAdsListItem : ListItem<UserAdsListItem> {

    data class AdListItem(val ad: Ad): UserAdsListItem() {
        override fun getViewType() = AD_VIEW_TYPE
    }

    data class UserListItem(val user: User): UserAdsListItem() {
        override fun getViewType() = USER_VIEW_TYPE
    }

    companion object {
        const val AD_VIEW_TYPE = 0
        const val USER_VIEW_TYPE = 1
    }
}