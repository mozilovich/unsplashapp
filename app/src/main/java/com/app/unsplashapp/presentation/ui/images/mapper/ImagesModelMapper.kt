package com.app.unsplashapp.presentation.ui.images.mapper

import com.app.unsplashapp.data.network.response.ImageResponse
import com.app.unsplashapp.presentation.extensions.toPresentation
import com.app.unsplashapp.presentation.ui.images.model.ImageModel

object ImagesModelMapper {
    fun map(result: ImageResponse): ImageModel = with(result) {
        ImageModel(
            id,
            urls?.small,
            description,
            likes,
            createdAt?.toPresentation(),
            user?.name,
            user?.location,
            user?.profileImage?.small,
        )
    }
}