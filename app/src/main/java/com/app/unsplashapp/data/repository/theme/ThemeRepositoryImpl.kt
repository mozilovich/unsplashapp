package com.app.unsplashapp.data.repository.theme

import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.emptyPreferences
import androidx.datastore.preferences.preferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ThemeRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : ThemeRepository {

    override fun getTheme(): Flow<Int> {
        return dataStore.data.catch {
            if (it is IOException) {
                it.printStackTrace()
                emit(emptyPreferences())
            } else {
                throw  it
            }
        }.map {
            it[KEY_THEME] ?: 0
        }
    }

    override suspend fun setTheme(themeValue: Int) {
        dataStore.edit {
            it[KEY_THEME] = themeValue
        }
    }

    companion object {
        private val KEY_THEME = preferencesKey<Int>("KEY_THEME")
    }
}