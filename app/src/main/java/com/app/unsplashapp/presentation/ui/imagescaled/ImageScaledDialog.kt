package com.app.unsplashapp.presentation.ui.imagescaled

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import com.app.unsplashapp.R
import com.app.unsplashapp.presentation.utils.image.ImageLoader
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.dialog_image_scaled.*
import javax.inject.Inject

@AndroidEntryPoint
class ImageScaledDialog : DialogFragment() {

    @Inject
    lateinit var imageLoader: ImageLoader

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_image_scaled, container, false)
    }

    override fun onResume() {
        super.onResume()
        dialog?.window?.apply {
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            attributes = attributes?.apply {
                width = ViewGroup.LayoutParams.MATCH_PARENT
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageScaled.clipToOutline = true

        val url = arguments?.getString(KEY_IMAGE_URL)
        imageLoader.loadScaledImage(imageScaled, url)

    }

    companion object {
        private const val KEY_IMAGE_URL = "KEY_IMAGE_URL"

        fun getInstance(imageUrl: String) = ImageScaledDialog().also {
            it.arguments = Bundle().apply {
                putString(KEY_IMAGE_URL, imageUrl)
            }
        }
    }
}