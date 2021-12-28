package org.romeo.layer_data.repository

import org.romeo.layer_data.data_sources.ApiDataSource
import org.romeo.layer_domain.entity.ad.Ads
import org.romeo.layer_domain.entity.ad.CreateEditAdEntity
import org.romeo.layer_domain.repository_bounderies.AdsRepository

class AdsRepositoryImpl(
    private val apiDataSource: ApiDataSource
) : AdsRepository {
    override suspend fun getAdsByUserId(userId: String, start: Int, end: Int): Ads =
        apiDataSource.getAdsByUserId(userId, start, end).await()

    override suspend fun getAds(start: Int, end: Int): Ads =
        apiDataSource.getAds(start, end).await()

    override suspend fun deleteAd(id: String) = apiDataSource.deleteAd(id).await()
    override suspend fun createEditAd(createEditAdEntity: CreateEditAdEntity) =
        apiDataSource.createEditAd(createEditAdEntity).await()

    override suspend fun getAd(id: String) = apiDataSource.getAd(id).await()

}