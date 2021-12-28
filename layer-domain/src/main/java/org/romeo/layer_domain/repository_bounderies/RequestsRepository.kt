package org.romeo.layer_domain.repository_bounderies

import org.romeo.layer_domain.entity.ad_user.AdUsers

interface RequestsRepository {
    suspend fun getRequests(): AdUsers
}