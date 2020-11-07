package com.app.unsplashapp.presentation.ui.images.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ImageModel(
    val id: String? = null,
    val url: String? = null,
    val description: String? = null,
    val likes: Int? = null,
    val createdAt: String? = null,
    val username: String? = null,
    val userLocation: String? = null,
    val userProfileUrl: String? = null,
): Parcelable