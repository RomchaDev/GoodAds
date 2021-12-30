package org.romeo.layer_data.api

import kotlinx.coroutines.Deferred
import org.romeo.layer_data.dto.ChangePricesRequest
import org.romeo.layer_domain.entity.ad.Ads
import org.romeo.layer_data.dto.LoginRequest
import org.romeo.layer_data.dto.TokenUser
import org.romeo.layer_data.dto.AdvertisingRequest
import org.romeo.layer_domain.entity.request_full.AdvertisingRequestFull
import org.romeo.layer_domain.entity.ad.Ad
import org.romeo.layer_domain.entity.ad.CreateEditAdEntity
import org.romeo.layer_domain.entity.request_full.AdvertisingRequestsFull
import org.romeo.layer_domain.entity.distribution.Distribution
import org.romeo.layer_domain.entity.user.User
import org.romeo.layer_domain.entity.user.Users
import retrofit2.http.*

interface ApiService {

    /**
     * Should take data from AdvertisingRequests table
     *
     * @return List of all the full requests where Request.advertiser id equals to id of current user
     * or AdvertisingRequest.adId is id of an ad which has Ad.userId equal to current user id
     * */
    @GET("api/advertising-requests")
    fun getAdvertisingRequests(): Deferred<AdvertisingRequestsFull>

    /**
     * Should take data from AdvertisingRequests table
     *
     * @param requestId id of the AdvertisingRequest that should be returned
     *
     * @return RequestFull, which has id the same as given in params
     * */
    @GET("api/advertising-requests/{requestId}")
    fun getAdvertisingRequest(@Path("requestId") requestId: String): Deferred<AdvertisingRequestFull>

    /**
     * Creates a new AdvertisingRequests in AdvertisingRequests table
     * Must generate requestId for each advertisingRequest
     *
     * @param advertisingRequest AdvertisingRequests
     * */
    @POST("api/advertising-requests")
    fun createAdvertisingRequest(@Body advertisingRequest: AdvertisingRequest): Deferred<Unit>

    /**
     * Deletes the AdvertisingRequest having id the same as given in params from AdvertisingRequests table
     *
     * @param requestId id of the user
     * */
    @DELETE("api/advertising-requests/{request-id}")
    fun declineAdvertisingRequest(@Path("request-id") requestId: String): Deferred<Unit>

    /**
     * Should change storyPrice and postPrice of current user in the TokenUsers table
     *
     * @param prices - object that contains new prices
     * */
    @PATCH("api/users")
    fun changePrices(@Body prices: ChangePricesRequest): Deferred<Unit>

    /**
     * Should return users from TokenUsers table, located between start and end numbers
     *
     * @param start index from which method must return data
     * @param end index until which method must return data
     *
     * @return object that contains all the users from table Users
     * */
    @GET("api/users")
    fun getAdvertisers(
        @Query("start") start: Int,
        @Query("end") end: Int
    ): Deferred<Users>

    /**
     * Takes the user from TokenUsers table that has id the same as given in params
     *
     * @param uid id of the user that should be returned
     *
     * @return user having id the same as given in params
     * */
    @GET("api/users/{id}")
    fun getUserById(@Path("id") uid: String): Deferred<User>

    /**
     * Takes user from TokenUser table that has token the same as given in header
     *
     * @return current user
     * */
    @GET("api/users/me")
    fun myUser(): Deferred<User>

    /**
     * Ads new user to Users table and generates him a new token.
     * Back-end should use instagram sdk to get the instagram user.
     * Now an example user could be returned
     *
     * @param auth an object containing user's instagram login and password
     *
     * @return user and it's generated token
     * */
    @POST("api/users")
    fun login(@Body auth: LoginRequest): Deferred<TokenUser>

    /**
     * Changes the ad or creates new one in Ads table.
     *
     * @param createEditAd - an object that contains Ad and list of byteArrays.
     * Each of byteArrays is an image, advertisable wants instagram user to see.
     * That list can be null.
     */
    @PUT("api/ads")
    fun createEditAd(@Body createEditAd: CreateEditAdEntity): Deferred<Unit>

    /**
     * Gets Ad from Ads table by it`s id.
     *
     * @param id id of the ad
     *
     * @return ad
     */
    @GET("api/ads/{id}")
    fun getAd(@Path("id") id: String): Deferred<Ad>

    /**
     * Deletes an ad from Ads table.
     *
     * @param id id of ad that must be deleted
     */
    @DELETE("api/ads/{id}")
    fun deleteAd(@Query("id") id: String): Deferred<Unit>

    /**
     * Gets number of ads that belong to particular user.
     *
     * @param start index from which method must return data
     * @param end index until which method must return data
     * @param userId id of particular user
     *
     * @return object that contains list of ads
     */
    @GET("api/ads/{userId}")
    fun getAdsByUserId(
        @Path("userId") userId: String,
        @Query("start") start: Int,
        @Query("end") end: Int
    ): Deferred<Ads>

    /**
     * Gets number of ads that belong to particular user.
     *
     * @param start index from which method must return data
     * @param end index to which method must return data
     *
     * @return object that contains list of ads
     */
    @GET("api/ads")
    fun getAds(
        @Query("start") start: Int,
        @Query("end") end: Int
    ): Deferred<Ads>

    /**
     * Ads new Distribution to Distributions table.
     * Sets places field = Distribution.advertisersAmount in Ad that has Ad.id equal to
     * Distribution.adId
     *
     * @param distribution distribution that should be used when reading the documentation above :)
     */
    @POST("api/distribution/create")
    fun createDistribution(@Body distribution: Distribution): Deferred<Unit>
}