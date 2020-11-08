package com.app.unsplashapp.domain.interactor

import com.app.unsplashapp.data.repository.theme.ThemeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ThemeInteractorImpl @Inject constructor(
    private val themeRepository: ThemeRepository
) : ThemeInteractor {

    override fun watchTheme(): Flow<Int> {
        return themeRepository.getTheme()
    }

    override fun getTheme(): Int {
        return runBlocking {
            themeRepository.getTheme().first()
        }
    }

    override suspend fun setTheme(themeValue: Int) {
        themeRepository.setTheme(themeValue)
    }
}