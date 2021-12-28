package org.romeo.layer_domain.repository_bounderies

import org.romeo.layer_domain.entity.request_full.RequestFull
import org.romeo.layer_domain.entity.request_full.RequestsFull

interface RequestsRepository {
    suspend fun getRequestsFull(): RequestsFull
    suspend fun createRequest(userId: String, adId: String)
    suspend fun getRequest(requestId: String): RequestFull
    suspend fun declineRequest(requestId: String)
}