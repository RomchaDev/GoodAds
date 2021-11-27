package org.romeo.layer_data.repository

import kotlinx.coroutines.Deferred
import org.romeo.layer_data.data_sources.ApiDataSource
import org.romeo.layer_data.data_sources.preferences.LoginResponseDataSourceLocal
import org.romeo.layer_data.dto.ChangePricesRequest
import org.romeo.layer_domain.repository_bounderies.UserRepository
import org.romeo.layer_data.dto.LoginRequest
import org.romeo.layer_data.dto.SendMyAdRequest
import org.romeo.layer_domain.entity.user.User

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

    override suspend fun getUser(uid: String) =
        apiDataSource.getUser(uid).await()

    override suspend fun getUsers(): List<User> = apiDataSource.getUsers().await()

    override suspend fun getToken() =
        loginResponseDataSource.get()?.token

    override suspend fun applyMyAd(userId: String, adId: String) =
        apiDataSource.sendMyAd(SendMyAdRequest(userId, adId)).await()

}