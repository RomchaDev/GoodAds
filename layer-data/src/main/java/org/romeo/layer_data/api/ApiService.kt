package org.romeo.layer_data.api

import kotlinx.coroutines.Deferred
import org.romeo.layer_data.dto.ChangePricesRequest
import org.romeo.layer_domain.entity.ad.Ads
import org.romeo.layer_data.dto.LoginRequest
import org.romeo.layer_data.dto.LoginResponse
import org.romeo.layer_data.dto.ApplyMyAdRequest
import org.romeo.layer_domain.entity.ad.Ad
import org.romeo.layer_domain.entity.ad.CreateEditAdEntity
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

    @GET("api/users/{id}")
    fun getUser(@Query("id") uid: String): Deferred<User>

    @GET("api/users")
    fun getUsers(): Deferred<List<User>>

    @POST("api/users/me/prices")
    fun changePrices(@Body prices: ChangePricesRequest): Deferred<Unit>

    @DELETE("api/my-ads/{id}")
    fun deleteAd(@Query("id") id: String): Deferred<Unit>

    @POST("api/users/send-my-ad")
    fun applyMyAd(@Body request: ApplyMyAdRequest): Deferred<Unit>

    @POST("api/my-ad-requests")
    fun getMyAdRequests(): Deferred<Ads>

    @POST("api/my-user-requests")
    fun getMyUserRequests(): Deferred<List<User>>

    @GET("api/ads/{id}")
    fun getAd(@Query("id") id: String): Deferred<Ad>

    @POST
    fun createEditAd(@Body createEditAd: CreateEditAdEntity): Deferred<Unit>
}