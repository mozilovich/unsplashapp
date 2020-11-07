package com.app.unsplashapp.presentation.utils.image

import android.widget.ImageView

interface ImageLoader {
    fun loadImage(target: ImageView, url: String?)
    fun loadAvatar(target: ImageView, url: String?)
    fun loadScaledImage(target: ImageView, url: String?)
}