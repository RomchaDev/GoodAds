package org.romeo.layer_data.repository

import org.romeo.layer_data.data_sources.ApiDataSource
import org.romeo.layer_data.data_sources.preferences.LoginResponseDataSourceLocal
import org.romeo.layer_data.dto.ChangePricesRequest
import org.romeo.layer_domain.repository_bounderies.UserRepository
import org.romeo.layer_data.dto.LoginRequest
import org.romeo.layer_domain.entity.user.User
import org.romeo.layer_domain.entity.user.Users

class UserRepositoryImpl(
    private val apiDataSource: ApiDataSource,
    private val loginResponseDataSource: LoginResponseDataSourceLocal
) : UserRepository {

    override suspend fun login(username: String, password: String): User {
        val response = apiDataSource.login(LoginRequest(username, password)).await()

        loginResponseDataSource.save(response)
        return response.user
    }

    override suspend fun changePrices(postPrice: Int, storyPrice: Int) =
        apiDataSource.changePrices(ChangePricesRequest(postPrice, storyPrice)).await()

    override suspend fun myUser() =
        loginResponseDataSource.get()?.user
            ?: apiDataSource.myUser().await()

    override suspend fun getUserById(uid: String) =
        apiDataSource.getUserById(uid).await()

    override suspend fun getAdvertisers(): Users = apiDataSource.getAdvertisers().await()

    override suspend fun getToken() =
        loginResponseDataSource.get()?.token

    override suspend fun getUserRequests() = apiDataSource.getUserRequests().await()

    override suspend fun declineUserRequest(userId: String) =
        apiDataSource.declineUserRequest(userId).await()
}