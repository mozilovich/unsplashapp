package com.app.unsplashapp.domain

import com.app.unsplashapp.domain.interactor.ThemeInteractor
import com.app.unsplashapp.domain.interactor.ThemeInteractorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class InteractorBinder {

    @Binds
    abstract fun bindThemeInteractor(interactorImpl: ThemeInteractorImpl): ThemeInteractor
}