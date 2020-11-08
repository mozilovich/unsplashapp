package com.app.unsplashapp.domain.interactor

import kotlinx.coroutines.flow.Flow


interface ThemeInteractor {
    fun watchTheme(): Flow<Int>
    fun getTheme(): Int
    suspend fun setTheme(themeValue: Int)
}