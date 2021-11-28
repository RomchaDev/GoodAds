package org.romeo.layer_presentation.core.utils

import android.content.Context
import android.net.Uri
import java.io.ByteArrayOutputStream
import java.io.InputStream

fun Uri.getByteArray(context: Context): ByteArray? {
    var fileByte: ByteArray? = null
    val steamIn: InputStream? = context.contentResolver.openInputStream(this)

    steamIn?.let {
        fileByte = getBytes(it)
    }
    steamIn?.close()

    return fileByte
}

private fun getBytes(inputStream: InputStream): ByteArray? {

    val byteBuff = ByteArrayOutputStream()
    val buffSize = 1024
    val buff = ByteArray(buffSize)
    var len: Int
    while (inputStream.read(buff).also { len = it } != -1) {
        byteBuff.write(buff, 0, len)
    }
    return byteBuff.toByteArray()

}