package com.app.unsplashapp.presentation.ui.settings

import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.app.unsplashapp.R
import com.app.unsplashapp.presentation.base.BaseFragment
import com.app.unsplashapp.presentation.extensions.dpToPx
import com.app.unsplashapp.presentation.extensions.needRedrawScreens
import com.app.unsplashapp.presentation.ui.MainActivity
import com.app.unsplashapp.presentation.ui.settings.model.NightModeType
import com.app.unsplashapp.presentation.utils.themeanimation.ThemeAnimationHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_settings.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SettingsFragment : BaseFragment(R.layout.fragment_settings) {

    @Inject
    lateinit var themeAnimationHelper: ThemeAnimationHelper

    private val viewModel: SettingsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.themeLiveData.observe(viewLifecycleOwner, { theme ->
            themePickGroup.getViews()
                .firstOrNull { it.text == theme.title }
                ?.isChecked = true
        })

        themePickGroup.getViews()
            .forEach { radioBtn ->
                radioBtn.setOnClickListener {
                    val oldTheme = viewModel.themeLiveData.value
                    val selectedTheme = NightModeType.fromName(radioBtn.text.toString())
                    if (selectedTheme != oldTheme) {
                        lifecycleScope.launch {
                            if (requireContext().needRedrawScreens(oldTheme, selectedTheme)) {
                                (activity as? MainActivity)?.apply {
                                    val location = IntArray(2)
                                    it.getLocationOnScreen(location)
                                    val locationX = it.width - requireContext().dpToPx(25)
                                    val locationY = location[1]
                                    themeAnimationHelper.showAnimation(locationX.toInt(), locationY, this)
                                }
                            }
                            viewModel.changeTheme(selectedTheme)
                            AppCompatDelegate.setDefaultNightMode(selectedTheme.value)
                            (activity as? MainActivity)?.refreshScreens()
                        }
                    }
                }
            }
    }

    fun RadioGroup.getViews() =
        (0 until childCount).map { getChildAt(it) }.filterIsInstance<RadioButton>()
}