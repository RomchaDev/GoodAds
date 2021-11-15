package org.romeo.layer_presentation.core.view

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import org.romeo.layer_data.image.GlideImageLoader

@BindingAdapter("load_image")
fun loadImage(img: ImageView, url: String) {
    val loader = GlideImageLoader()

    loader.loadImage(
        target = img,
        url = url
    )
}