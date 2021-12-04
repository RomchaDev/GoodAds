package org.romeo.layer_presentation.core.view

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.romeo.layer_data.image.GlideImageLoader

@BindingAdapter("load_image")
fun loadImage(img: ImageView, url: String) {
    val loader = GlideImageLoader()

    imageLoadingCoroutineScope.launch {
        loader.loadImage(
            target = img,
            url = url,
            imageInsertCoroutineScope
        )
    }
}

private val imageLoadingCoroutineScope = CoroutineScope(
    Dispatchers.IO
            + SupervisorJob()
)

private val imageInsertCoroutineScope = CoroutineScope(
    Dispatchers.Main
            + SupervisorJob()
)