package org.romeo.layer_data.data_sources

import kotlinx.coroutines.Deferred
import org.romeo.layer_data.api.ApiService
import org.romeo.layer_data.dto.ChangePricesRequest
import org.romeo.layer_data.dto.LoginRequest
import org.romeo.layer_domain.entity.ad.Ads
import org.romeo.layer_data.dto.Request
import org.romeo.layer_domain.entity.ad.CreateEditAdEntity
import org.romeo.layer_domain.entity.ad_user.AdUsers
import org.romeo.layer_domain.entity.distribution.Distribution
import org.romeo.layer_domain.entity.user.Users

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

    override fun createRequest(request: Request): Deferred<Unit> =
        service.createRequest(request)

    override fun advertiseOtherAd(adId: String): Deferred<Unit> = service.advertiseOtherAd(adId)

    override fun createEditAd(createEditAd: CreateEditAdEntity) = service.createEditAd(createEditAd)

    override fun getOtherAds(): Deferred<Ads> = service.getOtherAds()

    override fun getRequest(userId: String) = service.getRequest(userId)

    override fun getRequests() = service.getRequests()

    override fun createDistribution(distribution: Distribution) = service.createDistribution(distribution)

    override fun declineAd(adId: String) = service.declineAd(adId)

    override fun declineUserRequest(userId: String)  = service.declineUserRequest(userId)
}