package org.romeo.layer_domain.repository_bounderies

import org.romeo.layer_domain.entity.ad.Ad
import org.romeo.layer_domain.entity.ad.Ads


interface AdsRepository {
    suspend fun getMyAds(): Ads
    suspend fun getMyAdRequests(): Ads
    suspend fun getOtherAds(): Ads
    suspend fun deleteAd(id: String)
    suspend fun getAd(id: String): Ad
    suspend fun advertiseMyAd(userId: String, adId: String)
    suspend fun advertiseOtherAd(adId: String)
}
