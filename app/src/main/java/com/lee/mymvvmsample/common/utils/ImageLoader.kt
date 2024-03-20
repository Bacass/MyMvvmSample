package com.lee.mymvvmsample.common.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import javax.inject.Inject

class ImageLoader(private val context: Context) {

    fun imageLoadWithResourceID(resID: Int, v : ImageView) {
        Glide.with(this.context).load(resID).into(v)
    }

    fun imageLoadWithURL(url: String, v: ImageView) {
        Glide.with(this.context).load(url).into(v)
    }

    fun imageLoadWithFilePath(filePath: String, v: ImageView) {
        Glide.with(this.context).load(filePath).into(v)
    }

    fun imageLoadWithFile(f: File, v: ImageView) {
        Glide.with(this.context).load(f).into(v)
    }
}