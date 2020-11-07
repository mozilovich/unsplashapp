package com.app.unsplashapp.presentation.utils.image

import android.content.Context
import android.widget.ImageView
import com.app.unsplashapp.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class ImageLoaderImpl(private val context: Context) : ImageLoader {

    override fun loadImage(target: ImageView, url: String?) {
        Glide.with(context)
            .load(url)
            .skipMemoryCache(true)
            .placeholder(R.drawable.placeholder_image)
            .error(R.drawable.ic_error_gray)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(target)
    }

    override fun loadAvatar(target: ImageView, url: String?) {
        Glide.with(context)
            .load(url)
            .skipMemoryCache(true)
            .placeholder(R.drawable.ic_user)
            .error(R.drawable.ic_user)
            .dontAnimate()
            .into(target)
    }

    override fun loadScaledImage(target: ImageView, url: String?) {
        Glide.with(context)
            .load(url)
            .skipMemoryCache(true)
            .error(R.drawable.ic_error_gray)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(target)
    }
}