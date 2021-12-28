package org.romeo.layer_data.repository

import org.romeo.layer_data.data_sources.ApiDataSource
import org.romeo.layer_domain.repository_bounderies.RequestsRepository

class RequestsRepositoryImpl(
    private val apiDataSource: ApiDataSource
) : RequestsRepository {
    override suspend fun getRequests() = apiDataSource.getRequests().await()
}