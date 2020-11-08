package com.app.unsplashapp.presentation.ui.settings.model

import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.NightMode

enum class NightModeType(
    @NightMode val value: Int,
    val title: String,
) {
    NO(AppCompatDelegate.MODE_NIGHT_NO, "Светлая"),
    YES(AppCompatDelegate.MODE_NIGHT_YES, "Темная"),
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