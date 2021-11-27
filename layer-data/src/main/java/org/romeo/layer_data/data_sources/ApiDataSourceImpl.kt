package org.romeo.layer_data.data_sources

import kotlinx.coroutines.Deferred
import org.romeo.layer_data.api.ApiService
import org.romeo.layer_data.dto.ChangePricesRequest
import org.romeo.layer_data.dto.LoginRequest
import org.romeo.layer_domain.entity.ad.Ads
import org.romeo.layer_data.dto.SendMyAdRequest
import org.romeo.layer_domain.entity.user.User

class ApiDataSourceImpl(
    private val service: ApiService
) : ApiDataSource {
    override fun getMyAds() = service.getMyAds()

    override fun login(auth: LoginRequest) = service.login(auth)

    override fun myUser() = service.myUser()

    override fun getUser(uid: String) = service.getUser(uid)

    override fun getUsers(): Deferred<List<User>> = service.getUsers()

    override fun changePrices(prices: ChangePricesRequest): Deferred<Unit> =
        service.changePrices(prices)

    override fun deleteAd(id: String): Deferred<Unit> = service.deleteAd(id)

    override fun getAd(id: String) = service.getAd(id)

    override fun sendMyAd(request: SendMyAdRequest): Deferred<Unit> = service.sendMyAd(request)

    override fun getOtherAds(): Deferred<Ads> = service.getOtherAds()

    override fun getMyAdRequests(): Deferred<Ads> = service.getMyAdRequests()

    override fun getMyUserRequests(): Deferred<List<User>> = service.getMyUserRequests()
}