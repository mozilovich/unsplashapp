package com.app.unsplashapp.presentation.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Date.toPresentation(): String {
    val sdf = SimpleDateFormat("d MMMM", Locale("ru"))
    return sdf.format(this)
}