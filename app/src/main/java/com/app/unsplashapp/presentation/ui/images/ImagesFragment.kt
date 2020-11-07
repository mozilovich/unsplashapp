package com.app.unsplashapp.presentation.ui.images

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.paging.LoadState
import com.app.unsplashapp.R
import com.app.unsplashapp.presentation.base.BaseFragment
import com.app.unsplashapp.presentation.extensions.isVisible
import com.app.unsplashapp.presentation.extensions.showSnackbar
import com.app.unsplashapp.presentation.ui.images.adapter.ImagesAdapter
import com.app.unsplashapp.presentation.ui.images.adapter.ImagesDecorator
import com.app.unsplashapp.presentation.ui.images.adapter.ImagesLoadStateAdapter
import com.app.unsplashapp.presentation.ui.imagescaled.ImageScaledDialog
import com.google.android.material.transition.Hold
import com.google.android.material.transition.MaterialElevationScale
import com.google.android.material.transition.MaterialFadeThrough
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_images.*
import kotlinx.android.synthetic.main.layout_progress.containerProgress
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ImagesFragment : BaseFragment(R.layout.fragment_images) {

    @Inject
    lateinit var adapter: ImagesAdapter

    private val viewModel: ImagesViewModel by viewModels()

    private var dialogImageScaled: ImageScaledDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        enterTransition = MaterialFadeThrough().apply {
            duration = TRANSITION_DURATION
        }
    }

    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.doOnPreDraw { startPostponedEnterTransition() }
        postponeEnterTransition()

        imagesList.addItemDecoration(
            ImagesDecorator(resources.getDimensionPixelSize(R.dimen.item_image_offset))
        )
        imagesList.adapter = adapter.withLoadStateHeaderAndFooter(
            ImagesLoadStateAdapter(adapter::retry),
            ImagesLoadStateAdapter(adapter::retry),
        )

        adapter.onImageClick = { v, image ->
            exitTransition = Hold().apply {
                duration = TRANSITION_DURATION
            }
            reenterTransition = MaterialElevationScale(true).apply {
                duration = TRANSITION_DURATION
            }

            val action = ImagesFragmentDirections.actionImagesFragmentToImageDetailFragment(image)
            val extras = FragmentNavigatorExtras(v to image.id.orEmpty())
            navigate(action, extras)
        }

        adapter.onImageLongClickStart = {
            dialogImageScaled = ImageScaledDialog.getInstance(it).also { dialog ->
                dialog.show(childFragmentManager, TAG_DIALOG_IMAGE_SCALED)
            }
        }
        adapter.onImageLongClickEnd = {
            dialogImageScaled?.dismiss()
        }

        viewModel.images.observe(viewLifecycleOwner, {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        })

        lifecycleScope.launch {
            adapter.loadStateFlow.collectLatest {
                when (it.refresh) {
                    is LoadState.Loading -> containerProgress.isVisible = true
                    is LoadState.NotLoading -> containerProgress.isVisible = false
                    is LoadState.Error -> view.showSnackbar(it.toString())
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_images_search, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, findNavController())
                || super.onOptionsItemSelected(item)
    }

    companion object {
        private const val TAG_DIALOG_IMAGE_SCALED = "TAG_DIALOG_IMAGE_SCALED"
        private const val TRANSITION_DURATION = 300L
    }
}