package org.romeo.layer_presentation.main.requests

import org.romeo.layer_domain.app_state.AppStateEntity
import org.romeo.layer_domain.entity.list.items.UserAdsListItem

class RequestsViewState(val stateList: List<UserAdsListItem>) : AppStateEntity