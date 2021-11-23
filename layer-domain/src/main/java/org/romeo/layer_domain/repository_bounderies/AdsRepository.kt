package org.romeo.layer_domain.repository_bounderies

import org.romeo.layer_domain.entity.ad.Ads


interface AdsRepository {
    suspend fun getMyAds(): Ads
    suspend fun getMyAdRequests(): Ads
    suspend fun getOtherAds(): Ads
    suspend fun deleteAd(id: String)
}
