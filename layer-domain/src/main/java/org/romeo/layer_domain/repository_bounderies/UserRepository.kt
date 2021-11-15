package org.romeo.layer_domain.repository_bounderies

import org.romeo.layer_domain.entity.user.LoginResponse
import org.romeo.layer_domain.entity.user.User

interface UserRepository {
    suspend fun login(username: String, password: String): LoginResponse
    suspend fun saveLoginResponse(response: LoginResponse)
    suspend fun myUser(): User
    suspend fun getUsers(): List<User>
    suspend fun getToken(): String?
}