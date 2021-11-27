package org.romeo.layer_domain.entity.ad

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import org.romeo.layer_domain.app_state.AppStateEntity
import org.romeo.layer_domain.entity.list.Content
import org.romeo.layer_domain.entity.list.ListItem
import org.romeo.layer_domain.entity.list.ListItemId

@Parcelize
data class Ad(
    @ListItemId val id: String?,
    @Content var title: String,
    @Content var description: String,
    @Content var type: AdType,
    @Content val imageUrls: List<String>,
    val userId: String?,
) : ListItem<Ad>, AppStateEntity, Parcelable {

    @IgnoredOnParcel
    var typeStr = type.toString()
        set(value) {
            if (value == AdType.POST.toString()) AdType.POST
            else if (value == AdType.STORY.toString()) AdType.STORY
            field = value
        }


    companion object {
        fun emptyAd() = Ad(
            null,
            "",
            "",
            AdType.POST,
            listOf(),
            null
        )
    }
}
