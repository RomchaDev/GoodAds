package org.romeo.layer_data.data_sources

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.romeo.layer_data.dto.ChangePricesRequest
import org.romeo.layer_domain.entity.ad.Ad
import org.romeo.layer_domain.entity.ad.AdType
import org.romeo.layer_domain.entity.ad.Ads
import org.romeo.layer_data.dto.LoginRequest
import org.romeo.layer_data.dto.LoginResponse
import org.romeo.layer_domain.entity.user.User

class FakeApiDataSource : ApiDataSource {
    private val ads = mutableListOf<Ad>().apply {
        repeat(7) { i ->
            add(
                Ad(
                    "AD_ID_$i",
                    "UID_$i",
                    "hello_$i",
                    "world_$i",
                    if (i % 2 == 0) AdType.POST else AdType.STORY,
                    if (i % 2 == 0) "300$" else "500$",
                    11,
                    listOf(
                        if (i % 2 == 0)
                            "https://miro.medium.com/max/1200/1*mk1-6aYaf_Bes1E3Imhc0A.jpeg"
                        else
                            "https://orthostudio.ca/wp-content/uploads/2016/11/image-3.jpg"
                    )
                )
            )
        }
    }

    private val user = User(
        "SOME_LONG_UNREADABLE_ID",
        "Romeo2005",
        "Oleg",
        "I like to bake cookies",
        "https://interesnyefakty.org/wp-content/uploads/till-lindemann.jpg",
        "1000",
        "300",
        "29",
        300,
        420
    )

    private val user2 = User(
        "SOME_OTHER_LONG_UNREADABLE_ID",
        "Archi_228",
        "Vasya",
        "I like to swallow an ice cream",
        "https://interesnyefakty.org/wp-content/uploads/till-lindemann.jpg",
        "228",
        "300",
        "420",
        420,
        69
    )

    private val token = "SOME_REALLY_LONG_TOKEN"

    override fun getMyAds(): Deferred<Ads> = runBlocking {
        async {
            Ads(ads)
        }
    }

    override fun getOtherAds(): Deferred<Ads> = runBlocking {
        async {
            Ads(ads)
        }
    }

    override fun login(auth: LoginRequest) = runBlocking {
        async {
            LoginResponse(token, user)
        }
    }

    override fun myUser() = runBlocking {
        async { user }
    }

    override fun getUsers(): Deferred<List<User>> = runBlocking {
        async { listOf(user, user2) }
    }

    override fun changePrices(prices: ChangePricesRequest): Deferred<Unit> = runBlocking {
        async { Unit }
    }

}