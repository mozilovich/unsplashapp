package com.app.unsplashapp.presentation

import android.content.Context
import com.app.unsplashapp.data.repository.images.ImagesRepository
import com.app.unsplashapp.presentation.paging.ImagesPagingSource
import com.app.unsplashapp.presentation.utils.image.ImageLoader
import com.app.unsplashapp.presentation.utils.image.ImageLoaderImpl
import com.app.unsplashapp.presentation.utils.themeanimation.ThemeAnimationHelper
import com.app.unsplashapp.presentation.utils.themeanimation.ThemeAnimationHelperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ActivityComponent::class)
class PresentationModule {

    @Provides
    fun provideImageLoader(@ApplicationContext context: Context): ImageLoader = ImageLoaderImpl(context)

    @Provides
    fun provideImagesPagingSource(repository: ImagesRepository) = ImagesPagingSource(repository)

    @Provides
    fun provideThemeAnimationHelper(): ThemeAnimationHelper = ThemeAnimationHelperImpl()
}