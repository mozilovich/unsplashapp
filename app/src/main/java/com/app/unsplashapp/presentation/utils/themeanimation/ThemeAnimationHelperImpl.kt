package com.app.unsplashapp.presentation.utils.themeanimation

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.ViewAnimationUtils
import androidx.fragment.app.FragmentActivity
import com.app.unsplashapp.presentation.extensions.isVisible
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Math.hypot

class ThemeAnimationHelperImpl : ThemeAnimationHelper {

    override fun showAnimation(x: Int, y: Int, activity: FragmentActivity) {
        val container = activity.mainContainer
        val stub = activity.changeThemeStub

        val width = container.measuredWidth
        val height = container.measuredHeight

        val bitmap: Bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        container.draw(Canvas(bitmap))

        stub.setImageBitmap(bitmap)
        stub.isVisible = true

        val finalRadius = hypot(width.toDouble(), height.toDouble()).toFloat()
        ViewAnimationUtils.createCircularReveal(stub, x, y, finalRadius, 0f).apply {
            duration = 800
            addListener(object: AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    stub.setImageDrawable(null)
                    stub.isVisible = false
                }
            })
            start()
        }
    }
}