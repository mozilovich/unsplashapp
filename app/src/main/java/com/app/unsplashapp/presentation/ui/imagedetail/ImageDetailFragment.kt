package com.app.unsplashapp.presentation.ui.imagedetail

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.app.unsplashapp.R
import com.app.unsplashapp.data.network.UnsplashApi
import com.app.unsplashapp.presentation.base.BaseFragment
import com.app.unsplashapp.presentation.extensions.isVisible
import com.app.unsplashapp.presentation.extensions.themeColor
import com.app.unsplashapp.presentation.ui.MainActivity
import com.app.unsplashapp.presentation.ui.images.model.ImageModel
import com.app.unsplashapp.presentation.utils.image.ImageLoader
import com.google.android.material.transition.MaterialContainerTransform
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_image_detail.*
import javax.inject.Inject

@AndroidEntryPoint
class ImageDetailFragment : BaseFragment(R.layout.fragment_image_detail) {

    override var useDefaultToolbar = false

    @Inject
    lateinit var imageLoader: ImageLoader

    @Inject
    lateinit var api: UnsplashApi

    private val args: ImageDetailFragmentArgs by navArgs()
    private val image: ImageModel by lazy {
        args.model
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedElementEnterTransition = MaterialContainerTransform().apply {
            drawingViewId = R.id.fragmentContainer
            duration = TRANSITION_DURATION
            isElevationShadowEnabled = false
            setAllContainerColors(requireContext().themeColor(R.attr.colorSurface))
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbarImageDetail.apply {
            title = image.username
            subtitle = image.userLocation
            avatarUrl = image.userProfileUrl
            navBtnListener = {
                findNavController().navigateUp()
            }
        }

        imageLoader.loadImage(imageDetailImage, image.url)
        view.transitionName = image.id
        imageDetailDescriptionText.apply {
            isVisible = !image.description.isNullOrEmpty()
            text = image.description
        }
        imageDetailCreatedAtText.text = image.createdAt
        imageDetailLikesText.text = resources.getString(R.string.likes_text, image.likes)
    }

    companion object {
        private const val TRANSITION_DURATION = 300L
    }
}