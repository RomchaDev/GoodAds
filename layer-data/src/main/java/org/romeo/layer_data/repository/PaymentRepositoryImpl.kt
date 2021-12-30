package org.romeo.layer_data.repository

import org.romeo.layer_data.data_sources.ApiDataSource
import org.romeo.layer_domain.entity.payment.PayAdvertisingRequestEntity
import org.romeo.layer_domain.repository_bounderies.PaymentRepository

class PaymentRepositoryImpl(
    private val dataSource: ApiDataSource
) : PaymentRepository {
    override suspend fun payAdvertisingRequest(entity: PayAdvertisingRequestEntity) {
        dataSource.payAdvertisingRequest(entity).await()
    }
}