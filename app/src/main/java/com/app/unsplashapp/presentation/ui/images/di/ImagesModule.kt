package com.app.unsplashapp.presentation.ui.images.di

import com.app.unsplashapp.presentation.ui.images.adapter.ImagesAdapter
import com.app.unsplashapp.presentation.utils.image.ImageLoader
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
class ImagesModule {

    @Provides
    fun provideImagesAdapter(imageLoader: ImageLoader) = ImagesAdapter(imageLoader)
}