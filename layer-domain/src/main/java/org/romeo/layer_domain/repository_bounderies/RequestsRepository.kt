package org.romeo.layer_domain.repository_bounderies

import org.romeo.layer_domain.entity.request_full.RequestFull
import org.romeo.layer_domain.entity.request_full.RequestsFull

interface RequestsRepository {
    suspend fun getRequests(): RequestsFull
    suspend fun createRequest(userId: String, adId: String)
    suspend fun getRequest(userId: String): RequestFull
    suspend fun declineRequest(userId: String)
}