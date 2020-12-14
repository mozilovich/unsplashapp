package com.app.unsplashapp.presentation.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.app.unsplashapp.R
import com.app.unsplashapp.presentation.extensions.themeColor
import com.app.unsplashapp.presentation.ui.MainActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        imageSplash.startAnimation(
            AnimationUtils.loadAnimation(applicationContext, R.anim.anim_splash)
        )

        Handler(Looper.getMainLooper()).postDelayed({
            if (!isFinishing) {
                startActivity(Intent(this, MainActivity::class.java))
                overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up)
                finish()
            }
        }, SPLASH_DELAY)

        imageSplash.setColorFilter(themeColor(R.attr.colorPrimary))
    }

    companion object {
        private const val SPLASH_DELAY = 1000L
    }
}