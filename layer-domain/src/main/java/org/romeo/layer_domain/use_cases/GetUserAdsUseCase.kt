package org.romeo.layer_domain.use_cases

import org.romeo.layer_domain.entity.list.items.UserAdsListItem
import org.romeo.layer_domain.repository_bounderies.AdsRepository
import org.romeo.layer_domain.repository_bounderies.UserRepository

class GetUserAdsUseCase(private val adsRepository: AdsRepository, private val userRepository: UserRepository) {

    suspend fun execute(): List<UserAdsListItem> {
        val ads = adsRepository.getMyAds()
        val user = userRepository.myUser()

        val items = mutableListOf<UserAdsListItem>()
        items.add(UserAdsListItem.UserListItem(user))

        ads.adsList.forEach { ad ->
            items.add(UserAdsListItem.AdListItem(ad))
        }

        return items
    }

}