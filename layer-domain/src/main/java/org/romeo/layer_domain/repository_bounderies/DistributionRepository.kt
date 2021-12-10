package org.romeo.layer_domain.repository_bounderies

import org.romeo.layer_domain.entity.distribution.Distribution

interface DistributionRepository {
    suspend fun createDistribution(distribution: Distribution)
}