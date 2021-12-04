package org.romeo.layer_data.image

import android.widget.ImageView
import kotlinx.coroutines.CoroutineScope

interface ImageLoader<T> {
    fun loadImage(
        loadingCode: Int? = null,
        target: T,
        url: String,
        listener: ImageLoadingListener? = null
    )

    fun loadImage(
        target: ImageView,
        url: String,
        imageInsertScope: CoroutineScope
    )

    fun getImageBytes(
        url: String
    ): ByteArray
}