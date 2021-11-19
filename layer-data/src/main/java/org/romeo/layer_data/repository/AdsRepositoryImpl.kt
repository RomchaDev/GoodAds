package org.romeo.layer_data.repository

import org.romeo.layer_data.data_sources.ApiDataSource
import org.romeo.layer_domain.repository_bounderies.AdsRepository

class AdsRepositoryImpl(
    private val apiDataSource: ApiDataSource
) : AdsRepository {
    override suspend fun getMyAds() = apiDataSource.getMyAds().await()
    override suspend fun getOtherAds() = apiDataSource.getOtherAds().await()
    override suspend fun deleteAd(id: String) = apiDataSource.deleteAd(id).await()
}