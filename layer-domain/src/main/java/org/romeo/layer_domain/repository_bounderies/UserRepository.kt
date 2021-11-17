package org.romeo.layer_domain.repository_bounderies

import org.romeo.layer_domain.entity.user.User

interface UserRepository {
    suspend fun login(username: String, password: String): User
    suspend fun myUser(): User
    suspend fun getUsers(): List<User>
    suspend fun getToken(): String?
}