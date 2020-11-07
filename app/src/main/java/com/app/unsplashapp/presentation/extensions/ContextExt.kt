package com.app.unsplashapp.presentation.extensions

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.widget.Toast
import androidx.annotation.ColorInt
import com.google.android.material.snackbar.Snackbar

fun Context.showToast(text: String) = Toast.makeText(this, text, Toast.LENGTH_SHORT).show()

@SuppressLint("Recycle")
@ColorInt
fun Context.themeColor(resId: Int): Int {
    return obtainStyledAttributes(intArrayOf(resId)).getColor(0, Color.WHITE)
}