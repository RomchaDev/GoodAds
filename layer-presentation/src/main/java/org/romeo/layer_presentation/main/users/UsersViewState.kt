package org.romeo.layer_presentation.main.users

import org.romeo.layer_domain.app_state.AppStateEntity
import org.romeo.layer_domain.entity.user.User

class UsersViewState(val stateList: List<User>) : AppStateEntity