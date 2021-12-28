package org.romeo.layer_domain.repository_bounderies

import org.romeo.layer_domain.entity.user.User
import org.romeo.layer_domain.entity.user.Users

interface UserRepository {
    suspend fun login(username: String, password: String): User
    suspend fun changePrices(postPrice: Int, storyPrice: Int)
    suspend fun myUser(): User
    suspend fun getUserById(uid: String): User
    suspend fun getAdvertisers(): Users
    suspend fun getToken(): String?
}