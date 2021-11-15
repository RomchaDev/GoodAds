package org.romeo.layer_data.image

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import org.romeo.repository.image.ImageLoadingListener
import com.bumptech.glide.request.target.Target

class GlideImageLoader : ImageLoader<ImageView> {
    override fun loadImage(
        loadingCode: Int?,
        target: ImageView,
        url: String,
        listener: ImageLoadingListener?
    ) {
        Glide.with(target)
            .load(url)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ) = listener?.onLoadingError(loadingCode, e) ?: false

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ) = listener?.onLoadingSuccess(loadingCode) ?: false
            })
            .into(target)
    }
}