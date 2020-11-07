package com.app.unsplashapp.presentation.ui.images.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ImagesDecorator(private val offset: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildLayoutPosition(view)
        if (position % 3 == 1) {
            outRect.left = offset
            outRect.right = offset
        }
        outRect.top = offset
    }
}