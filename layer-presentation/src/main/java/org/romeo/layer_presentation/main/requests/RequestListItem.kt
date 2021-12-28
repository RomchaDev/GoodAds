package org.romeo.layer_presentation.main.requests

import org.romeo.layer_domain.entity.ad.Ad
import org.romeo.layer_domain.entity.list.ListItem
import org.romeo.layer_domain.entity.user.User

sealed class RequestListItem : ListItem<RequestListItem> {
    abstract val requestId: String

    data class UserRequestListItem(
        override val requestId: String,
        val user: User
    ) : RequestListItem() {
        override fun getViewType() = USER_VIEW_TYPE
    }

    data class AdRequestListItem(
        override val requestId: String,
        val ad: Ad
    ) : RequestListItem() {
        override fun getViewType() = AD_VIEW_TYPE
    }

    companion object {
        const val AD_VIEW_TYPE = 0
        const val USER_VIEW_TYPE = 1
    }
}