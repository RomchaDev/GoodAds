package org.romeo.layer_data.api

import kotlinx.coroutines.Deferred
import org.romeo.layer_data.dto.ChangePricesRequest
import org.romeo.layer_domain.entity.ad.Ads
import org.romeo.layer_data.dto.LoginRequest
import org.romeo.layer_data.dto.LoginResponse
import org.romeo.layer_data.dto.AdRequest
import org.romeo.layer_domain.entity.AdUser
import org.romeo.layer_domain.entity.ad.Ad
import org.romeo.layer_domain.entity.ad.CreateEditAdEntity
import org.romeo.layer_domain.entity.distribution.Distribution
import org.romeo.layer_domain.entity.user.User
import org.romeo.layer_domain.entity.user.Users
import retrofit2.http.*

interface ApiService {

    /**
     * should take data from UserRequests table
     * @return List of all the users that want to advertise ad of a user given by token
     * */
    @GET("api/users/user-requests")
    fun getUserRequests(): Deferred<Users>

    /**
     * Should take data from UserRequests table
     *
     * @param userId - id of the advertiser that wants to advertise current user's ad
     * @return AdUser where Ad is the ad of the current user that is going to be advertised
     * and User is the user that wants to advertise the ad
     * */
    @GET("api/users/user-requests/{userId}")
    fun getUserRequest(@Path("userId") userId: String): Deferred<AdUser>

    /**
     * Creates new AdRequest in AdRequests table
     *
     * @param request - ad request
     * */
    @POST("api/ad-request")
    fun createAdRequest(@Body request: AdRequest): Deferred<Unit>

    /**
     * Should change storyPrice and postPrice of current user in the table Users
     *
     * @param prices - object that contains new prices
     * */
    @PATCH("api/users/my-user/prices")
    fun changePrices(@Body prices: ChangePricesRequest): Deferred<Unit>

    /**
     * Now user cannot choose whether he is interested in advertising or he just wants to be
     * advertised, so this method should return ALL users from table Users. Later pagination
     * will be added.
     *
     * @return object that contains all the users from table Users
     * */
    @GET("api/users/advertisers")
    fun getAdvertisers(): Deferred<Users>

    /**
     * Takes the user from table Users that has id given in params
     *
     * @param uid - id of the user that should be returned
     * @return user having id the same as given in params
     * */
    @GET("api/users/{id}")
    fun getUserById(@Path("id") uid: String): Deferred<User>

    /**
     * Takes user from table user that has token the same as given in header
     *
     * @return current user
     * */
    @GET("api/users/my-user")
    fun myUser(): Deferred<User>

    /**
     * Ads new user to Users table and generates him a new token
     * back-end should use instagram sdk to get the instagram user.
     * Now an example user could be returned
     *
     * @param auth - an object containing user's instagram login and password
     * @return user and it's generated token
     * */
    @POST("api/users/login")
    fun login(@Body auth: LoginRequest): Deferred<LoginResponse>

    /**
     * Deletes the user given by id from the UserRequests table.
     * Now id of the advertiser is given, later it will be changed to id of the request that
     * is going to be declined.
     *
     * @param userId - id of the user
     * */
    @DELETE("api/users/decline/{id}")
    fun declineUserRequest(@Path("id") userId: String): Deferred<Unit>

    /**
     * Deletes the ad given by id from the AdRequests table.
     *
     * @param adId - id of the ad that must be declined
     */
    @DELETE("api/ads/decline/{id}")
    fun declineAd(@Path("id") adId: String): Deferred<Unit>

    /**
     * Changes the ad or creates new one in Ads table.
     *
     * @param createEditAd - an object that contains Ad and list of images that
     * advertiser wants instagram users to see.
     * That list can be null.
     */
    @POST("api/ads")
    fun createEditAd(@Body createEditAd: CreateEditAdEntity): Deferred<Unit>

    /**
     * Gets Ad from Ads table by it`s id.
     *
     * @param id - id of ad
     * @return ad
     */
    @GET("api/ads/{id}")
    fun getAd(@Path("id") id: String): Deferred<Ad>

    /**
     * Gets user`s ad requests from AdRequests table by his token.
     *
     * @return ads - an object that contains list of ads
     */
    @GET("api/ads/ad-requests")
    fun getAdRequests(): Deferred<Ads>

    /**
     * Creates a user request into UserRequests table.
     *
     * @param adId - id of ad that user wants to advertise
     */
    @POST("api/ads/advertise-other-ad/{ad-id}")
    fun advertiseOtherAd(@Path("ad-id") adId: String): Deferred<Unit>

    /**
     * Deletes an ad from Ads table.
     *
     * @param id - id of ad that must be deleted
     */
    @DELETE("api/ads/{id}")
    fun deleteAd(@Query("id") id: String): Deferred<Unit>

    /**
     * Gets ALL ads from Ads table.
     *
     * @return object that contains list of ads
     */
    @GET("api/ads/other-ads")
    fun getOtherAds(): Deferred<Ads>

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
    @POST("api/distribution/create")
    fun createDistribution(@Body distribution: Distribution): Deferred<Unit>
}