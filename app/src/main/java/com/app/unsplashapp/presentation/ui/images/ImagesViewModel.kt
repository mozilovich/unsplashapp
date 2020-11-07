package com.app.unsplashapp.presentation.ui.images

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.app.unsplashapp.presentation.paging.ImagesPagingSource
import kotlinx.coroutines.ExperimentalCoroutinesApi

class ImagesViewModel @ViewModelInject constructor(
    private val imagesPagingSource: ImagesPagingSource,
) : ViewModel() {

    @ExperimentalCoroutinesApi
    val images = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE, maxSize = MAX_SIZE),
        pagingSourceFactory = { imagesPagingSource }
    ).flow
        .asLiveData()
        .cachedIn(viewModelScope)

    companion object {
        private const val PAGE_SIZE = 20
        private const val MAX_SIZE = 100
    }
}