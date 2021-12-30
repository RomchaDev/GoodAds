package org.romeo.layer_domain.entity.payment

data class PayAdvertisingRequestEntity(
    val requestId: String,
    val payment: PaymentEntity
)
