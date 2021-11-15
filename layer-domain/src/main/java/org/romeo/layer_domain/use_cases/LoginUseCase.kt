package org.romeo.layer_domain.use_cases

import org.romeo.layer_domain.entity.user.User

interface LoginUseCase {
    suspend fun login(username: String, password: String): User
}