package com.app.unsplashapp.presentation.ui.images.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.unsplashapp.R
import com.app.unsplashapp.presentation.ui.images.model.ImageModel
import com.app.unsplashapp.presentation.utils.image.ImageLoader
import kotlinx.android.synthetic.main.item_image.view.*

class ImagesAdapter(private val imageLoader: ImageLoader) :
    PagingDataAdapter<ImageModel, ImagesAdapter.ImageViewHolder>(IMAGES_COMPARATOR) {

    var onImageClick: ((View, ImageModel) -> Unit)? = null
    var onImageLongClickStart: ((String) -> Unit)? = null
    var onImageLongClickEnd: (() -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
        return ImageViewHolder(v)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var isNeedToScale = false

        @SuppressLint("ClickableViewAccessibility")
        fun bind(item: ImageModel) = with(itemView) {
            ViewCompat.setTransitionName(this, item.id)

            imageLoader.loadImage(itemImage, item.url)
            itemImage.setOnClickListener {
                onImageClick?.invoke(this, item)
            }
            itemImage.setOnLongClickListener {
                item.url?.let { imageUrl ->
                    onImageLongClickStart?.invoke(imageUrl)
                    isNeedToScale = true
                }
                true
            }
            itemImage.setOnTouchListener { _, event ->
                if (event.action == MotionEvent.ACTION_UP && isNeedToScale) {
                    isNeedToScale = false
                    onImageLongClickEnd?.invoke()
                    true
                } else {
                    false
                }
            }
        }
    }

    companion object {
        private val IMAGES_COMPARATOR = object : DiffUtil.ItemCallback<ImageModel>() {
            override fun areContentsTheSame(oldItem: ImageModel, newItem: ImageModel) =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: ImageModel, newItem: ImageModel) =
                oldItem.id == newItem.id
        }
    }
}