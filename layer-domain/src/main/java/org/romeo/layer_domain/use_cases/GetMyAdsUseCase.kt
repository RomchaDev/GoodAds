package org.romeo.layer_domain.use_cases

import org.romeo.layer_domain.entity.ad.Ads
import org.romeo.layer_domain.repository_bounderies.AdsRepository
import org.romeo.layer_domain.repository_bounderies.UserRepository

class GetMyAdsUseCase(
    private val adsRepository: AdsRepository,
    private val userRepository: UserRepository
) {

    suspend fun execute(start: Int, end: Int): Ads =
        adsRepository.getAdsByUserId(userRepository.myUser().id, start, end)

}