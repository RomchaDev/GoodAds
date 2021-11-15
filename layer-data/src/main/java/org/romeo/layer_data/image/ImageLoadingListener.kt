package org.romeo.repository.image

interface ImageLoadingListener {
    fun onLoadingSuccess(loadingCode: Int?): Boolean
    fun onLoadingError(loadingCode: Int?, t: Throwable?): Boolean
}