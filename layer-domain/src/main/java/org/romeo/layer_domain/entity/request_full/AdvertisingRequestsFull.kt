package org.romeo.layer_domain.entity.request_full

import org.romeo.layer_domain.app_state.AppStateEntity

data class AdvertisingRequestsFull(val requests: List<AdvertisingRequestFull>): AppStateEntity