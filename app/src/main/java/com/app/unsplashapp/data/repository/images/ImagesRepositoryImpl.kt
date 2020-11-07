package com.app.unsplashapp.data.repository.images

import com.app.unsplashapp.data.network.UnsplashApi
import com.app.unsplashapp.data.network.response.ImageResponse
import com.app.unsplashapp.presentation.paging.ImagesPagingSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImagesRepositoryImpl @Inject constructor(private val api: UnsplashApi) : ImagesRepository {

    override suspend fun getPhotos(page: Int, perPage: Int): List<ImageResponse> {
        return api.getImages(page, perPage)
    }
}