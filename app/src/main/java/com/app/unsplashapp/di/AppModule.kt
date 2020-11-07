package com.app.unsplashapp.di

import android.content.Context
import com.app.unsplashapp.UnsplashApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class AppModule {

    /*@Singleton
    @Provides
    fun provideAppContext(app: UnsplashApplication): Context {
        return app.applicationContext
    }*/
}