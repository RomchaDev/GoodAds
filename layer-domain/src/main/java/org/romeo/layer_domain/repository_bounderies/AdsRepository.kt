package org.romeo.layer_domain.repository_bounderies

import org.romeo.layer_domain.entity.ad.Ad
import org.romeo.layer_domain.entity.ad.Ads
import org.romeo.layer_domain.entity.ad.CreateEditAdEntity


interface AdsRepository {
    suspend fun getMyAds(): Ads
    suspend fun getMyAdRequests(): Ads
    suspend fun getOtherAds(): Ads
    suspend fun deleteAd(id: String)
    suspend fun createEditAd(createEditAdEntity: CreateEditAdEntity)
    suspend fun getAd(id: String): Ad
}
