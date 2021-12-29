package org.romeo.layer_domain.use_cases

import org.romeo.layer_domain.entity.AdUser
import org.romeo.layer_domain.repository_bounderies.AdsRepository
import org.romeo.layer_domain.repository_bounderies.UserRepository

class GetAdUserUseCase(
    private val adsRepository: AdsRepository,
    private val userRepository: UserRepository
) {
    suspend fun execute(adId: String): AdUser {
        val ad = adsRepository.getAd(adId)
        val user = userRepository.getUserById(ad.userId!!)
        return AdUser(ad, user)
    }
}