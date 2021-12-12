package org.romeo.layer_data.api

import kotlinx.coroutines.Deferred
import org.romeo.layer_data.dto.ChangePricesRequest
import org.romeo.layer_domain.entity.ad.Ads
import org.romeo.layer_data.dto.LoginRequest
import org.romeo.layer_data.dto.LoginResponse
import org.romeo.layer_data.dto.ApplyAdRequest
import org.romeo.layer_domain.entity.AdUser
import org.romeo.layer_domain.entity.ad.Ad
import org.romeo.layer_domain.entity.ad.CreateEditAdEntity
import org.romeo.layer_domain.entity.distribution.Distribution
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
    fun advertiseMyAd(@Body request: ApplyAdRequest): Deferred<Unit>

    @POST("api/users/send-other-ad/{id}")
    fun advertiseOtherAd(@Query("id") id: String): Deferred<Unit>

    @GET("api/my-ad-requests")
    fun getMyAdRequests(): Deferred<Ads>

    @GET("api/my-user-requests/{userId}")
    fun getMyUserRequest(@Query("userId") userId: String): Deferred<AdUser>

    @POST("api/my-user-requests")
    fun getMyUserRequests(): Deferred<List<User>>

    @GET("api/ads/{id}")
    fun getAd(@Query("id") id: String): Deferred<Ad>

    @POST("api/ads/create-edit")
    fun createEditAd(@Body createEditAd: CreateEditAdEntity): Deferred<Unit>

    @POST("api/distribution/create")
    fun createDistribution(@Body distribution: Distribution): Deferred<Unit>

    @DELETE("api/ads/decline/{id}")
    fun declineAd(@Query("id") adId: String): Deferred<Unit>

    @DELETE("api/users/decline/{id}")
    fun declineUser(@Query("id") userId: String): Deferred<Unit>
}