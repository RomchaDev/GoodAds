package org.romeo.layer_domain.entity.user

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import org.romeo.layer_domain.app_state.AppStateEntity
import org.romeo.layer_domain.entity.list.ListItem

@Parcelize
data class User(
    val id: String,
    val nickName: String,
    val name: String,
    val bio: String,
    val avatarUrl: String,
    val posts: String,
    val followers: String,
    val following: String,
    val postPrice: String,
    val storyPrice: String
): Parcelable, ListItem<User>, AppStateEntity