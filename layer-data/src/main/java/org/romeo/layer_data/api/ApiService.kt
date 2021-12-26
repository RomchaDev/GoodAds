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
import org.romeo.layer_domain.entity.user.Users
import retrofit2.http.*

interface ApiService {
    @GET("api/ads/my-ads")
    fun getMyAds(): Deferred<Ads>

    @GET("api/ads/other-ads")
    fun getOtherAds(): Deferred<Ads>

    @POST("api/users/login")
    fun login(@Body auth: LoginRequest): Deferred<LoginResponse>

    @GET("api/users/my-user")
    fun myUser(): Deferred<User>

    @GET("api/users/{id}")
    fun getUserById(@Query("id") uid: String): Deferred<User>

    @GET("api/users/advertisers")
    fun getAdvertisers(): Deferred<Users>

    @POST("api/users/my-user/prices")
    fun changePrices(@Body prices: ChangePricesRequest): Deferred<Unit>

    @DELETE("api/ads/{id}")
    fun deleteAd(@Query("id") id: String): Deferred<Unit>

    @POST("api/users/send-my-ad")
    fun advertiseMyAd(@Body request: ApplyAdRequest): Deferred<Unit>

    @POST("api/ads/advertise-other-ad/{id}")
    fun advertiseOtherAd(@Query("id") id: String): Deferred<Unit>

    @GET("api/ad-requests")
    fun getAdRequests(): Deferred<Ads>

    @GET("api/user-requests/{userId}")
    fun getUserRequest(@Query("userId") userId: String): Deferred<AdUser>

    @GET("api/user-requests")
    fun getUserRequests(): Deferred<Users>

    @GET("api/ads/{id}")
    fun getAd(@Query("id") id: String): Deferred<Ad>

    @POST("api/ads")
    fun createEditAd(@Body createEditAd: CreateEditAdEntity): Deferred<Unit>

    @POST("api/distribution/create")
    fun createDistribution(@Body distribution: Distribution): Deferred<Unit>

    @DELETE("api/ads/decline/{id}")
    fun declineAd(@Query("id") adId: String): Deferred<Unit>

    @DELETE("api/users/decline/{id}")
    fun declineUserRequest(@Query("id") userId: String): Deferred<Unit>
}