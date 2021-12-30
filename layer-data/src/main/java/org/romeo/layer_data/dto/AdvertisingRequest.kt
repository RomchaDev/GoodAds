package org.romeo.layer_data.dto

data class AdvertisingRequest(
    val advertiserId: String,
    val adId: String,
    val takeUserPrice: Boolean = false
)