package com.mobiversal.movieapplication.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

class ImageLoader {
    companion object {
        fun loadImage(url: String, imageView: ImageView, context: Context) {
            Glide.with(context).load(url).into(imageView) //cu ajutorul librariei glide incarcam imaginile
        }
    }
}