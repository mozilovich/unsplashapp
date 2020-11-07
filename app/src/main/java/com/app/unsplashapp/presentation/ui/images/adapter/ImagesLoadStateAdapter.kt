package com.app.unsplashapp.presentation.ui.images.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.unsplashapp.R
import com.app.unsplashapp.presentation.extensions.isVisible
import kotlinx.android.synthetic.main.item_images_load_state.view.*

class ImagesLoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<ImagesLoadStateAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_images_load_state, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(loadState: LoadState) = with(itemView) {
            loadStateErrorBtn.apply {
                isVisible = loadState is LoadState.Error
                setOnClickListener { retry.invoke() }
            }
            loadStateErrorText.isVisible = loadState is LoadState.Error
            loadStateProgress.isVisible = loadState is LoadState.Loading
        }
    }
}