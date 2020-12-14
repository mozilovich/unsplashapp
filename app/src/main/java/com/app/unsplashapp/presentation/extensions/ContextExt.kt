package com.app.unsplashapp.presentation.extensions

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import android.graphics.Color
import android.util.TypedValue
import android.widget.Toast
import androidx.annotation.ColorInt
import com.app.unsplashapp.presentation.ui.settings.model.NightModeType

fun Context.showToast(text: String) = Toast.makeText(this, text, Toast.LENGTH_SHORT).show()

@SuppressLint("Recycle")
@ColorInt
fun Context.themeColor(resId: Int): Int {
    return obtainStyledAttributes(intArrayOf(resId)).getColor(0, Color.WHITE)
}

fun Context.needRedrawScreens(oldTheme: NightModeType?, newTheme: NightModeType?): Boolean {
    return !((oldTheme == NightModeType.SYSTEM && newTheme == getSystemTheme()) ||
            newTheme == NightModeType.SYSTEM && oldTheme == getSystemTheme())
}

fun Context.getSystemTheme(): NightModeType {
    return if ((applicationContext.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) ==
        Configuration.UI_MODE_NIGHT_YES
    ) {
        NightModeType.YES
    } else {
        NightModeType.NO
    }
}

fun Context.dpToPx(valueInDp: Int): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        valueInDp.toFloat(),
        resources.displayMetrics
    )
}