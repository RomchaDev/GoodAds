package org.romeo.layer_domain.entity.request_full

import org.romeo.layer_domain.app_state.AppStateEntity

data class RequestsFull(val adUsers: List<RequestFull>): AppStateEntity