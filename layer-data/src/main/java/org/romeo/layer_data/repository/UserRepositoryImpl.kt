package org.romeo.layer_data.repository

import org.romeo.layer_data.data_sources.ApiDataSource
import org.romeo.layer_data.data_sources.preferences.LoginResponseDataSourceLocal
import org.romeo.layer_domain.repository_bounderies.UserRepository
import org.romeo.layer_data.dto.LoginRequest
import org.romeo.layer_data.dto.LoginResponse
import org.romeo.layer_domain.entity.user.User

class UserRepositoryImpl(
    private val apiDataSource: ApiDataSource,
    private val loginResponseDataSource: LoginResponseDataSourceLocal,
) : UserRepository {

    override suspend fun login(username: String, password: String): User {
        val response = apiDataSource.login(LoginRequest(username, password)).await()

        loginResponseDataSource.save(response)
        return response.user
    }

    override suspend fun myUser() =
        loginResponseDataSource.get()?.user
            ?: apiDataSource.myUser().await()

    override suspend fun getUsers(): List<User> = apiDataSource.getUsers().await()

    override suspend fun getToken() =
        loginResponseDataSource.get()?.token
}