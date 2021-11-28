package org.romeo.layer_domain.use_cases

import org.romeo.layer_domain.entity.AdUser
import org.romeo.layer_domain.repository_bounderies.AdsRepository
import org.romeo.layer_domain.repository_bounderies.UserRepository

class GetUserAdUseCase(
    private val adsRepository: AdsRepository,
    private val userRepository: UserRepository
) {
    suspend fun execute(userId: String): AdUser {
        val user = userRepository.getUser(userId)
        val ad = adsRepository.getAd(user.adId)
        return AdUser(ad, user)
    }
}