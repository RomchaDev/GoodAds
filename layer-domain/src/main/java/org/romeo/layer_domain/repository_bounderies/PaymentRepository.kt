package org.romeo.layer_domain.repository_bounderies

import org.romeo.layer_domain.entity.payment.PayAdvertisingRequestEntity

interface PaymentRepository {
    suspend fun payAdvertisingRequest(entity: PayAdvertisingRequestEntity)
}