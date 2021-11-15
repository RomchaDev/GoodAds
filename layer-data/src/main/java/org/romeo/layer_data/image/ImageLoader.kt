package org.romeo.layer_data.image

import org.romeo.repository.image.ImageLoadingListener

interface ImageLoader<T> {
    fun loadImage(
        loadingCode: Int? = null,
        target: T,
        url: String,
        listener: ImageLoadingListener? = null
    )
}