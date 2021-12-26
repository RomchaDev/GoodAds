package org.romeo.layer_domain.repository_bounderies

import org.romeo.layer_domain.entity.AdUser
import org.romeo.layer_domain.entity.ad.Ad
import org.romeo.layer_domain.entity.ad.Ads
import org.romeo.layer_domain.entity.ad.CreateEditAdEntity


interface AdsRepository {
    suspend fun getMyAds(): Ads
    suspend fun getAdRequests(): Ads
    suspend fun getOtherAds(): Ads
    suspend fun deleteAd(id: String)
    suspend fun createEditAd(createEditAdEntity: CreateEditAdEntity)
    suspend fun getAd(id: String): Ad
    suspend fun advertiseMyAd(userId: String, adId: String)
    suspend fun advertiseOtherAd(adId: String)
    suspend fun getMyUserRequest(userId: String): AdUser
    suspend fun declineAd(adId: String)
}
