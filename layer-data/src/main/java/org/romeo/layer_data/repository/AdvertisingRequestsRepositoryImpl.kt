package org.romeo.layer_data.repository

import org.romeo.layer_data.data_sources.ApiDataSource
import org.romeo.layer_data.dto.AdvertisingRequest
import org.romeo.layer_domain.repository_bounderies.AdvertisingRequestsRepository

class AdvertisingRequestsRepositoryImpl(
    private val apiDataSource: ApiDataSource
) : AdvertisingRequestsRepository {
    override suspend fun getAdvertisingRequestsFull() = apiDataSource.getAdvertisingRequests().await()

    override suspend fun createAdvertisingRequest(userId: String, adId: String, takeUserPrice: Boolean) =
        apiDataSource.createAdvertisingRequest(AdvertisingRequest(userId, adId, takeUserPrice)).await()

    override suspend fun getAdvertisingRequest(requestId: String) =
        apiDataSource.getAdvertisingRequest(requestId).await()

    override suspend fun declineAdvertisingRequest(requestId: String) =
        apiDataSource.declineAdvertisingRequest(requestId).await()
}