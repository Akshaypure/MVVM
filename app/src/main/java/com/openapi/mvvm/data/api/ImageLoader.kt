package com.openapi.mvvm.data.api

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

object ImageLoader {
    fun loadImage(imageView: ImageView, context: Context, url: String){
        Glide.with(context)
            .load(url)
            .into(imageView)
            .clearOnDetach()
    }

    fun clearImage(imageView: ImageView, context: Context){
        Glide.with(context).clear(imageView)
        imageView.setImageDrawable(null)
    }
}