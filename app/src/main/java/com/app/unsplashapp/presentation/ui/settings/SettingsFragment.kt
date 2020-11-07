package com.app.unsplashapp.presentation.ui.settings

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.app.unsplashapp.R
import com.app.unsplashapp.presentation.ui.MainActivity
import com.app.unsplashapp.presentation.base.BaseFragment
import com.app.unsplashapp.presentation.ui.settings.model.NightModeType
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_settings.*
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SettingsFragment : BaseFragment(R.layout.fragment_settings) {

    private val viewModel: SettingsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.theme.observe(viewLifecycleOwner, { theme ->
            themePickGroup.getViews()
                .firstOrNull { it.text == theme.title }
                ?.isChecked = true
        })

        themePickGroup.getViews()
            .forEach { radioBtn ->
                radioBtn.setOnClickListener {
                    val theme = NightModeType.fromName(radioBtn.text.toString())
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

                    lifecycleScope.launch {
                        viewModel.onThemeChanged(theme)
                    }
                    (activity as? MainActivity)?.doSmth()
                }
            }
    }

    fun RadioGroup.getViews() =
        (0 until childCount).map { getChildAt(it) }.filterIsInstance<RadioButton>()
}