package org.romeo.layer_data.data_sources

import android.util.Log
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.romeo.layer_data.dto.ChangePricesRequest
import org.romeo.layer_data.dto.LoginRequest
import org.romeo.layer_data.dto.TokenUser
import org.romeo.layer_domain.entity.ad.Ad
import org.romeo.layer_domain.entity.ad.AdType
import org.romeo.layer_domain.entity.ad.Ads
import org.romeo.layer_data.dto.Request
import org.romeo.layer_domain.entity.ad_user.AdUser
import org.romeo.layer_domain.entity.user.User
import org.romeo.layer_domain.entity.ad.CreateEditAdEntity
import org.romeo.layer_domain.entity.ad_user.AdUsers
import org.romeo.layer_domain.entity.distribution.Distribution
import org.romeo.layer_domain.entity.user.Users

class FakeApiDataSource : ApiDataSource {
    private val ads = mutableListOf<Ad>().apply {
        repeat(7) { i ->
            add(
                Ad(
                    "AD_ID_$i",
                    "Title_$i",
                    "hello world_$i",
                    if (i % 2 == 0) AdType.POST else AdType.STORY,
                    listOf(
                        if (i % 2 == 0)
                            "https://miro.medium.com/max/1200/1*mk1-6aYaf_Bes1E3Imhc0A.jpeg"
                        else
                            "https://orthostudio.ca/wp-content/uploads/2016/11/image-3.jpg",
                        if (i % 2 == 0)
                            "https://orthostudio.ca/wp-content/uploads/2016/11/image-3.jpg"
                        else
                            "https://miro.medium.com/max/1200/1*mk1-6aYaf_Bes1E3Imhc0A.jpeg"


                    ), "UID_$i",
                    "300$",
                    11
                )
            )
        }
    }

    private val user = User(
        "UID_1",
        "Romeo2005",
        "Oleg",
        "I like to bake cookies",
        "https://interesnyefakty.org/wp-content/uploads/till-lindemann.jpg",
        "1000",
        "300",
        "29",
        "300",
        "420"
    )

    private val user2 = User(
        "UID_2",
        "Archi_228",
        "Vasya",
        "I like to swallow an ice cream",
        "https://interesnyefakty.org/wp-content/uploads/till-lindemann.jpg",
        "228",
        "300",
        "420",
        "420",
        "69"
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
            TokenUser(token, user)
        }
    }

    override fun myUser() = runBlocking {
        async { user }
    }

    override fun getAdvertisers(): Deferred<Users> = runBlocking {
        async { Users(listOf(user, user2)) }
    }

    override fun changePrices(prices: ChangePricesRequest): Deferred<Unit> = runBlocking {
        async { Unit }
    }

    override fun deleteAd(id: String): Deferred<Unit> = runBlocking {
        async {
            ads.removeIf { ad -> ad.id == id }
            return@async Unit
        }
    }

    override fun createRequest(request: Request): Deferred<Unit> = runBlocking {
        async { Unit }
    }

    override fun getAd(id: String) = runBlocking {
        async {
            ads.find { ad -> ad.id == id }!!
        }
    }

    override fun createEditAd(createEditAd: CreateEditAdEntity): Deferred<Unit> = runBlocking {
        async {
            Log.d(TAG, "createEditAd: ad id:${createEditAd.ad.id}")
            Unit
        }
    }

    override fun getUserById(uid: String) = runBlocking {
        async {
            if (uid.last().code % 2 == 0) user
            else user2
        }
    }

    override fun getRequests(): Deferred<AdUsers> = runBlocking {
        async { AdUsers(listOf(AdUser(ads[1], user), AdUser(ads[2], user2))) }
    }

    override fun createDistribution(distribution: Distribution) = runBlocking {
        async {
            //Creating a distribution
        }
    }

    override fun getRequest(userId: String) = runBlocking {
        async { AdUser(ads[0], getUserById(userId).await()) }
    }

    override fun declineRequest(userId: String) = runBlocking {
        async {
            //Declining a user
        }
    }

    companion object {
        private const val TAG = "FAKE_API_DATA_SOURCE_TAG"
    }
}