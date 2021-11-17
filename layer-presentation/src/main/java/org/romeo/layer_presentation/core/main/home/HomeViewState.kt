package org.romeo.layer_presentation.core.main.home

import org.romeo.layer_domain.app_state.AppStateEntity
import org.romeo.layer_domain.entity.list.items.HomeListItem

class HomeViewState(val stateList: List<HomeListItem>) : AppStateEntity