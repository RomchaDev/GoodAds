package org.romeo.layer_data.repository

import org.romeo.layer_data.data_sources.ApiDataSource
import org.romeo.layer_data.dto.Request
import org.romeo.layer_domain.repository_bounderies.RequestsRepository

class RequestsRepositoryImpl(
    private val apiDataSource: ApiDataSource
) : RequestsRepository {

    override suspend fun createRequest(userId: String, adId: String) =
        apiDataSource.createRequest(Request(userId, adId)).await()

    override suspend fun getRequest(userId: String) =
        apiDataSource.getRequest(userId).await()

    override suspend fun declineRequest(userId: String) =
        apiDataSource.declineRequest(userId).await()

}