package org.romeo.layer_data.data_sources

import kotlinx.coroutines.Deferred
import org.romeo.layer_data.api.ApiService
import org.romeo.layer_data.dto.ChangePricesRequest
import org.romeo.layer_data.dto.LoginRequest
import org.romeo.layer_domain.entity.ad.Ads
import org.romeo.layer_data.dto.AdvertisingRequest
import org.romeo.layer_domain.entity.ad.CreateEditAdEntity
import org.romeo.layer_domain.entity.distribution.Distribution
import org.romeo.layer_domain.entity.payment.PayAdvertisingRequestEntity

class ApiDataSourceImpl(
    private val service: ApiService
) : ApiDataSource {

    override fun login(auth: LoginRequest) = service.login(auth)

    override fun myUser() = service.myUser()

    override fun getUserById(uid: String) = service.getUserById(uid)

    override fun getAdvertisers(
        start: Int,
        end: Int
    ) = service.getAdvertisers(start, end)

    override fun changePrices(prices: ChangePricesRequest): Deferred<Unit> =
        service.changePrices(prices)

    override fun deleteAd(id: String): Deferred<Unit> = service.deleteAd(id)

    override fun getAd(id: String) = service.getAd(id)

    override fun createAdvertisingRequest(advertisingRequest: AdvertisingRequest): Deferred<Unit> =
        service.createAdvertisingRequest(advertisingRequest)

    override fun createEditAd(createEditAd: CreateEditAdEntity) = service.createEditAd(createEditAd)

    override fun getAdsByUserId(userId: String, start: Int, end: Int): Deferred<Ads> =
        service.getAdsByUserId(userId, start, end)

    override fun getAds(start: Int, end: Int): Deferred<Ads> = service.getAds(start, end)

    override fun getAdvertisingRequest(requestId: String) = service.getAdvertisingRequest(requestId)

    override fun getAdvertisingRequests() = service.getAdvertisingRequests()

    override fun declineAdvertisingRequest(requestId: String) = service.declineAdvertisingRequest(requestId)

    override fun createDistribution(distribution: Distribution) =
        service.createDistribution(distribution)

    override fun payAdvertisingRequest(entity: PayAdvertisingRequestEntity) = service.payAdvertisingRequest(entity)
}