package org.romeo.layer_domain.repository_bounderies

import org.romeo.layer_domain.entity.ad_user.AdUser
import org.romeo.layer_domain.entity.ad_user.AdUsers

interface RequestsRepository {
    suspend fun getRequests(): AdUsers
    suspend fun createRequest(userId: String, adId: String)
    suspend fun getRequest(userId: String): AdUser
    suspend fun declineRequest(userId: String)
}