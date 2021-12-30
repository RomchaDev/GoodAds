package org.romeo.layer_domain.entity.payment

data class PaymentEntity(
    val card_number: String,
    val expiry_date: String,
    val cvv2: String
)