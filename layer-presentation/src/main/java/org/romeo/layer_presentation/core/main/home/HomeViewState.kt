package org.romeo.layer_presentation.core.main.home

import org.romeo.layer_domain.app_state.AppStateEntity
import org.romeo.layer_domain.entity.list.items.UserAdsListItem

class HomeViewState(val stateList: List<UserAdsListItem>) : AppStateEntity