package org.romeo.layer_data.api

import kotlinx.coroutines.Deferred
import org.romeo.layer_domain.entity.ad.Ads
import org.romeo.layer_data.dto.LoginRequest
import org.romeo.layer_data.dto.LoginResponse
import org.romeo.layer_domain.entity.user.User
import retrofit2.http.*

interface ApiService {
    @GET("api/my-ads")
    fun getMyAds(): Deferred<Ads>

    @GET("api/other-ads")
    fun getOtherAds(): Deferred<Ads>

    @POST("api/users/login")
    fun login(@Body auth: LoginRequest): Deferred<LoginResponse>

    @GET("api/users/me")
    fun myUser(): Deferred<User>

    @GET("api/users")
    fun getUsers(): Deferred<List<User>>

}