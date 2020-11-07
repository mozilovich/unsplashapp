package com.app.unsplashapp.data.repository.theme

import kotlinx.coroutines.flow.Flow

interface ThemeRepository {
    fun getTheme(): Flow<Int>
    suspend fun setTheme(themeValue: Int)
}