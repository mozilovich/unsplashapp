package com.app.unsplashapp.presentation.ui

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.app.unsplashapp.R
import com.app.unsplashapp.domain.interactor.ThemeInteractor
import com.app.unsplashapp.presentation.extensions.isVisible
import com.app.unsplashapp.presentation.extensions.refreshCurrentTab
import com.app.unsplashapp.presentation.extensions.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var currentNavController: LiveData<NavController>? = null

    private var currentTheme: Int? = null

    @Inject
    lateinit var interactor: ThemeInteractor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar_main)
        setupBottomNavigation()

        currentTheme = resources.configuration.uiMode
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        if (interactor.getTheme() == AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM) {
            refreshScreens()
        }
    }

    fun refreshScreens() {
        if (currentTheme != resources.configuration.uiMode) {
            bottomNavigation.refreshCurrentTab(supportFragmentManager)

            toolbar_main.apply {
                setBackgroundColor(ContextCompat.getColor(context, R.color.colorSurface))
                setTitleTextColor(ContextCompat.getColor(context, R.color.colorPrimary))
            }

            bottomNavigation.apply {
                backgroundTintList = ContextCompat.getColorStateList(context, R.color.colorSurface)
                itemIconTintList =
                    ContextCompat.getColorStateList(context, R.color.bottom_view_tab_color)
                itemTextColor =
                    ContextCompat.getColorStateList(context, R.color.bottom_view_tab_color)
            }

            currentTheme = resources.configuration.uiMode

            Log.e("screens", "refreshed")
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

    fun isDarkMode(): Boolean {
        val modeFlag = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        return modeFlag == Configuration.UI_MODE_NIGHT_YES
    }
}