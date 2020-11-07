package com.app.unsplashapp.data.repository.images

import androidx.paging.PagingData
import com.app.unsplashapp.data.Result
import com.app.unsplashapp.data.network.response.ImageResponse
import kotlinx.coroutines.flow.Flow

interface ImagesRepository {
    suspend fun getPhotos(page: Int, perPage: Int): List<ImageResponse>
}