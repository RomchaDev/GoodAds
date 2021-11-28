package org.romeo.layer_data.repository

import org.romeo.layer_data.data_sources.ApiDataSource
import org.romeo.layer_data.dto.ApplyAdRequest
import org.romeo.layer_domain.entity.ad.Ad
import org.romeo.layer_domain.entity.ad.Ads
import org.romeo.layer_domain.repository_bounderies.AdsRepository

class AdsRepositoryImpl(
    private val apiDataSource: ApiDataSource
) : AdsRepository {
    override suspend fun getMyAds() = apiDataSource.getMyAds().await()
    override suspend fun getOtherAds() = apiDataSource.getOtherAds().await()
    override suspend fun deleteAd(id: String) = apiDataSource.deleteAd(id).await()
    override suspend fun getAd(id: String) = apiDataSource.getAd(id).await()
    override suspend fun getMyAdRequests(): Ads = apiDataSource.getMyAdRequests().await()

    override suspend fun advertiseMyAd(userId: String, adId: String) =
        apiDataSource.advertiseMyAd(ApplyAdRequest(userId, adId)).await()

    override suspend fun advertiseOtherAd(adId: String) =
        apiDataSource.advertiseOtherAd(adId).await()
}