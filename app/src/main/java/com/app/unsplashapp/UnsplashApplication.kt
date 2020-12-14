package com.app.unsplashapp

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.app.unsplashapp.domain.interactor.ThemeInteractor
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class UnsplashApplication : Application() {

    @Inject
    lateinit var themeInteractor: ThemeInteractor

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(themeInteractor.getTheme())
    }
}