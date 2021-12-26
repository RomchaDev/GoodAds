package org.romeo.layer_domain.entity.user

import org.romeo.layer_domain.app_state.AppStateEntity

data class Users(val users: List<User>): AppStateEntity