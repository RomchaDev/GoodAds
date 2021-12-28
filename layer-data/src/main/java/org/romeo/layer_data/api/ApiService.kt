package org.romeo.layer_data.api

import kotlinx.coroutines.Deferred
import org.romeo.layer_data.dto.ChangePricesRequest
import org.romeo.layer_domain.entity.ad.Ads
import org.romeo.layer_data.dto.LoginRequest
import org.romeo.layer_data.dto.TokenUser
import org.romeo.layer_data.dto.Request
import org.romeo.layer_domain.entity.request_full.RequestFull
import org.romeo.layer_domain.entity.ad.Ad
import org.romeo.layer_domain.entity.ad.CreateEditAdEntity
import org.romeo.layer_domain.entity.request_full.RequestsFull
import org.romeo.layer_domain.entity.distribution.Distribution
import org.romeo.layer_domain.entity.user.User
import org.romeo.layer_domain.entity.user.Users
import retrofit2.http.*

interface ApiService {

    /**
     * should take data from Requests table
     *
     * @return List of all the full requests where Request.advertiser id equals to id of current user
     * or Request.adId is id of an ad which has Ad.userId equal to current user
     * */
    @GET("api/requests")
    fun getRequests(): Deferred<RequestsFull>

    /**
     * Should take data from Requests table
     *
     * @param requestId - id of the request that should be returned
     *
     * @return RequestFull, which has id the same as given in params
     * */
    @GET("api/requests/{requestId}")
    fun getRequest(@Path("requestId") requestId: String): Deferred<RequestFull>

    /**
     * Creates a new Request in Requests table
     *
     * @param request - ad request
     * */
    @POST("api/requests")
    fun createRequest(@Body request: Request): Deferred<Unit>

    /**
     * Deletes the request having id the same as given in params from Requests table
     *
     * @param requestId id of the user
     * */
    @DELETE("api/requests/{request-id}")
    fun declineRequest(@Path("request-id") requestId: String): Deferred<Unit>

    /**
     * Should change storyPrice and postPrice of current user in the TokenUsers table
     *
     * @param prices - object that contains new prices
     * */
    @PATCH("api/users")
    fun changePrices(@Body prices: ChangePricesRequest): Deferred<Unit>

    /**
     * Should return ALL the users from TokenUsers table. Later pagination
     * will be added.
     *
     * @return object that contains all the users from table Users
     * */
    @GET("api/users")
    fun getAdvertisers(): Deferred<Users>//TODO

    /**
     * Takes the user from TokenUsers table that has id the same as given in params
     *
     * @param uid - id of the user that should be returned
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
     * @param auth - an object containing user's instagram login and password
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
    @POST("api/ads")
    fun createEditAd(@Body createEditAd: CreateEditAdEntity): Deferred<Unit>//TODO

    /**
     * Gets Ad from Ads table by it`s id.
     *
     * @param id - id of the ad
     * @return ad
     */
    @GET("api/ads/{id}")
    fun getAd(@Path("id") id: String): Deferred<Ad>

    /**
     * Deletes an ad from Ads table.
     *
     * @param id - id of ad that must be deleted
     */
    @DELETE("api/ads/{id}")
    fun deleteAd(@Query("id") id: String): Deferred<Unit>

    /**
     * Gets ALL ads from Ads table except for the ads belonging to current user.
     *
     * @return object that contains list of ads
     */
    @GET("api/ads")
    fun getOtherAds(): Deferred<Ads>//TODO

    /**
     * Gets all user`s ads by his token.
     *
     * @return object that contains list of ads
     */
    @GET("api/ads/my-ads")
    fun getMyAds(): Deferred<Ads>

    /**
     * Deprecated.
     * Use createEditAd instead
     */
    @Deprecated("Automatically creating when a new ad is created")
    @POST("api/distribution/create")
    fun createDistribution(@Body distribution: Distribution): Deferred<Unit> //TODO
}