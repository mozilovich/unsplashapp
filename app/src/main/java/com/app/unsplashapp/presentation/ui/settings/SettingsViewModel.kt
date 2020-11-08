package com.app.unsplashapp.presentation.ui.settings

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.app.unsplashapp.domain.interactor.ThemeInteractor
import com.app.unsplashapp.presentation.ui.settings.model.NightModeType
import kotlinx.coroutines.flow.map

class SettingsViewModel @ViewModelInject constructor(
    private val themeInteractor: ThemeInteractor
) : ViewModel() {

    val themeLiveData = themeInteractor.watchTheme()
        .map { NightModeType.fromValue(it) }
        .asLiveData()

    suspend fun changeTheme(theme: NightModeType) {
        themeInteractor.setTheme(theme.value)
    }
}