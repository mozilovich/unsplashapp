package com.app.unsplashapp.presentation.ui.settings

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.app.unsplashapp.data.repository.theme.ThemeRepository
import com.app.unsplashapp.presentation.ui.settings.model.NightModeType
import kotlinx.coroutines.flow.map

class SettingsViewModel @ViewModelInject constructor(
    private val themeRepository: ThemeRepository
) : ViewModel() {

    val theme = themeRepository.getTheme()
        .map { NightModeType.fromValue(it) }
        .asLiveData()

    suspend fun onThemeChanged(theme: NightModeType) {
        themeRepository.setTheme(theme.value)
    }
}