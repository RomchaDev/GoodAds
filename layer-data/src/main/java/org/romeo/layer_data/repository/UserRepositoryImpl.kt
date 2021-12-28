package org.romeo.layer_data.repository

import org.romeo.layer_data.data_sources.ApiDataSource
import org.romeo.layer_data.data_sources.preferences.TokenUserDataSourceLocal
import org.romeo.layer_data.dto.ChangePricesRequest
import org.romeo.layer_domain.repository_bounderies.UserRepository
import org.romeo.layer_data.dto.LoginRequest
import org.romeo.layer_domain.entity.user.User
import org.romeo.layer_domain.entity.user.Users

class UserRepositoryImpl(
    private val apiDataSource: ApiDataSource,
    private val tokenUserDataSource: TokenUserDataSourceLocal
) : UserRepository {

    override suspend fun login(username: String, password: String): User {
        val response = apiDataSource.login(LoginRequest(username, password)).await()

        tokenUserDataSource.save(response)
        return response.user
    }

    override suspend fun changePrices(postPrice: Int, storyPrice: Int) =
        apiDataSource.changePrices(ChangePricesRequest(postPrice, storyPrice)).await()

    override suspend fun myUser() =
        tokenUserDataSource.get()?.user
            ?: apiDataSource.myUser().await()

    override suspend fun getUserById(uid: String) =
        apiDataSource.getUserById(uid).await()

    override suspend fun getAdvertisers(
        start: Int,
        end: Int
    ): Users = apiDataSource.getAdvertisers(start, end).await()

    override suspend fun getToken() =
        tokenUserDataSource.get()?.token
}