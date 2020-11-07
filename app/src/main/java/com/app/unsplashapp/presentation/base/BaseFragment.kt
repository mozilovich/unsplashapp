package com.app.unsplashapp.presentation.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import com.app.unsplashapp.presentation.ui.MainActivity

abstract class BaseFragment(layoutId: Int) : Fragment(layoutId) {

    open var useDefaultToolbar = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? MainActivity)?.showDefaultToolbar(useDefaultToolbar)
    }

    protected fun navigate(
        destination: NavDirections,
        extraInfo: FragmentNavigator.Extras? = null
    ) {
        if (extraInfo != null) {
            findNavController().navigate(destination, extraInfo)
        } else {
            findNavController().navigate(destination, extraInfo)
        }
    }
}