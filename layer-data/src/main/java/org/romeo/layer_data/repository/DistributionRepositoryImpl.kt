package org.romeo.layer_data.repository

import org.romeo.layer_data.data_sources.ApiDataSource
import org.romeo.layer_domain.entity.distribution.Distribution
import org.romeo.layer_domain.repository_bounderies.DistributionRepository

class DistributionRepositoryImpl(
    private val apiDataSource: ApiDataSource
) : DistributionRepository {
    override suspend fun createDistribution(distribution: Distribution) {
        apiDataSource.createDistribution(distribution).await()
    }
}