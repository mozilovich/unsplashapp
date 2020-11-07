package com.app.unsplashapp.presentation.paging

import androidx.paging.PagingSource
import com.app.unsplashapp.data.repository.images.ImagesRepository
import com.app.unsplashapp.presentation.ui.images.mapper.ImagesModelMapper
import com.app.unsplashapp.presentation.ui.images.model.ImageModel

class ImagesPagingSource(private val repository: ImagesRepository) :
    PagingSource<Int, ImageModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ImageModel> {
        val position = params.key ?: START_PAGE

        return try {
            val response = repository.getPhotos(position, params.loadSize).map(ImagesModelMapper::map)
            LoadResult.Page(
                response,
                if (position == START_PAGE) null else position - 1,
                if (response.isEmpty()) null else position + 1,
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    companion object {
        private const val START_PAGE = 1
    }
}