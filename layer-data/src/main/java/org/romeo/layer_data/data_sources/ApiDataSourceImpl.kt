package org.romeo.layer_data.data_sources

import kotlinx.coroutines.Deferred
import org.romeo.layer_data.api.ApiService
import org.romeo.layer_data.dto.ChangePricesRequest
import org.romeo.layer_data.dto.LoginRequest
import org.romeo.layer_domain.entity.ad.Ads
import org.romeo.layer_data.dto.ApplyAdRequest
import org.romeo.layer_domain.entity.ad.CreateEditAdEntity
import org.romeo.layer_domain.entity.distribution.Distribution
import org.romeo.layer_domain.entity.user.User

class ApiDataSourceImpl(
    private val service: ApiService
) : ApiDataSource {
    override fun getMyAds() = service.getMyAds()

    override fun login(auth: LoginRequest) = service.login(auth)

    override fun myUser() = service.myUser()

    override fun getUserById(uid: String) = service.getUserById(uid)

    override fun getAdvertisers() = service.getAdvertisers()

    override fun changePrices(prices: ChangePricesRequest): Deferred<Unit> =
        service.changePrices(prices)

    override fun deleteAd(id: String): Deferred<Unit> = service.deleteAd(id)

    override fun getAd(id: String) = service.getAd(id)

    override fun advertiseMyAd(request: ApplyAdRequest): Deferred<Unit> =
        service.advertiseMyAd(request)

    override fun advertiseOtherAd(id: String): Deferred<Unit> = service.advertiseOtherAd(id)

    override fun createEditAd(createEditAd: CreateEditAdEntity) = service.createEditAd(createEditAd)

    override fun getOtherAds(): Deferred<Ads> = service.getOtherAds()

    override fun getAdRequests(): Deferred<Ads> = service.getAdRequests()

    override fun getUserRequest(userId: String) = service.getUserRequest(userId)

    override fun getUserRequests(): Deferred<List<User>> = service.getUserRequests()

    override fun createDistribution(distribution: Distribution) = service.createDistribution(distribution)

    override fun declineAd(adId: String) = service.declineAd(adId)

    override fun declineUserRequest(userId: String)  = service.declineUserRequest(userId)
}