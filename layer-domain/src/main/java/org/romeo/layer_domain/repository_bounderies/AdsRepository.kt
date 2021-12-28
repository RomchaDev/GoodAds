package org.romeo.layer_domain.repository_bounderies

import org.romeo.layer_domain.entity.ad.Ad
import org.romeo.layer_domain.entity.ad.Ads
import org.romeo.layer_domain.entity.ad.CreateEditAdEntity


interface AdsRepository {
    suspend fun getAdsByUserId(userId: String, start: Int, end: Int): Ads
    suspend fun getAds(start: Int, end: Int): Ads
    suspend fun deleteAd(id: String)
    suspend fun createEditAd(createEditAdEntity: CreateEditAdEntity)
    suspend fun getAd(id: String): Ad
}
