package org.romeo.layer_domain.repository_bounderies

import org.romeo.layer_domain.entity.request_full.AdvertisingRequestFull
import org.romeo.layer_domain.entity.request_full.AdvertisingRequestsFull

interface AdvertisingRequestsRepository {
    suspend fun getAdvertisingRequestsFull(): AdvertisingRequestsFull
    suspend fun createAdvertisingRequest(userId: String, adId: String, takeUserPrice: Boolean = false)
    suspend fun getAdvertisingRequest(requestId: String): AdvertisingRequestFull
    suspend fun declineAdvertisingRequest(requestId: String)
}