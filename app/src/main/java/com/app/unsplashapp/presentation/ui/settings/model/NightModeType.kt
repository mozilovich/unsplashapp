package com.app.unsplashapp.presentation.ui.settings.model

import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.NightMode
import com.app.unsplashapp.R

enum class NightModeType(
    @NightMode val value: Int,
    val title: String,
) {
    NO(AppCompatDelegate.MODE_NIGHT_NO, "Темная"),
    YES(AppCompatDelegate.MODE_NIGHT_YES, "Светлая"),
    SYSTEM(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM, "Системная");

    companion object {

        fun fromValue(@NightMode value: Int): NightModeType {
            return values().firstOrNull {
                it.value == value
            } ?: SYSTEM
        }

        fun fromName(name: String?): NightModeType {
            return values().firstOrNull {
                it.title == name
            } ?: SYSTEM
        }
    }
}