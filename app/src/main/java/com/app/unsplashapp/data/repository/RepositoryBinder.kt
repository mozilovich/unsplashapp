package com.app.unsplashapp.data.repository

import com.app.unsplashapp.data.repository.images.ImagesRepository
import com.app.unsplashapp.data.repository.images.ImagesRepositoryImpl
import com.app.unsplashapp.data.repository.theme.ThemeRepository
import com.app.unsplashapp.data.repository.theme.ThemeRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class RepositoryBinder {

    @Binds
    abstract fun bindImagesRepository(repositoryImpl: ImagesRepositoryImpl): ImagesRepository

    @Binds
    abstract fun bindThemeRepository(repositoryImpl: ThemeRepositoryImpl): ThemeRepository
}