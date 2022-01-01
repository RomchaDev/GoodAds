package org.romeo.layer_domain.entity.payment

data class BankCard(
    val card_number: String,
    val cardHolderName: String,
    val expiry_date: String,
    val cvv2: String
)