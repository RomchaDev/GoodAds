package org.romeo.layer_data.data_sources

import kotlinx.coroutines.Deferred
import org.romeo.layer_data.api.ApiService
import org.romeo.layer_domain.entity.ad.Ads
import org.romeo.layer_domain.entity.user.LoginRequest
import org.romeo.layer_domain.entity.user.User

class ApiDataSourceImpl(
    private val service: ApiService
) : ApiDataSource {
    override fun getMyAds() = service.getMyAds()

    override fun login(auth: LoginRequest) = service.login(auth)

    override fun myUser() = service.myUser()

    override fun getUsers(): Deferred<List<User>> = service.getUsers()

    override fun getOtherAds(): Deferred<Ads> = service.getOtherAds()
}