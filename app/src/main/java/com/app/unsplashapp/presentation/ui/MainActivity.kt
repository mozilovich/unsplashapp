package com.app.unsplashapp.presentation.ui

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.app.unsplashapp.R
import com.app.unsplashapp.presentation.extensions.isVisible
import com.app.unsplashapp.presentation.extensions.refreshCurrentTab
import com.app.unsplashapp.presentation.extensions.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var currentNavController: LiveData<NavController>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar_main)
        setupBottomNavigation()
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        bottomNavigation.refreshCurrentTab(supportFragmentManager)

        toolbar_main.apply {
            setBackgroundColor(ContextCompat.getColor(context, R.color.colorSurface))
            setTitleTextColor(ContextCompat.getColor(context, R.color.colorPrimary))
        }

        bottomNavigation.apply {
            backgroundTintList = ContextCompat.getColorStateList(context, R.color.colorSurface)
            itemIconTintList = ContextCompat.getColorStateList(context, R.color.bottom_view_tab_color)
            itemTextColor = ContextCompat.getColorStateList(context, R.color.bottom_view_tab_color)
        }
    }

    private fun setupBottomNavigation() {
        val navGraphIds =
            listOf(R.navigation.nav_users, R.navigation.nav_images, R.navigation.nav_settings)

        // Setup the bottom navigation view with a list of navigation graphs
        val controller = bottomNavigation.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.fragmentContainer,
            intent = intent
        )

        // Whenever the selected controller changes, setup the action bar.
        controller.observe(this, { navController ->
            setupActionBarWithNavController(navController)
        })
        currentNavController = controller
    }

    fun showDefaultToolbar(show: Boolean) {
        toolbar_main.isVisible = show
    }
}