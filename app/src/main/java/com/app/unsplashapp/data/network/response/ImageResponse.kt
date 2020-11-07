package com.app.unsplashapp.data.network.response

import com.google.gson.annotations.SerializedName
import java.util.*

class ImageResponse {
    @SerializedName("id")
    val id: String? = null
    @SerializedName("created_at")
    val createdAt: Date? = null
    @SerializedName("likes")
    val likes: Int? = null
    @SerializedName("description")
    val description: String? = null
    @SerializedName("urls")
    val urls: ImageUrlResponse? = null
    @SerializedName("user")
    val user: UserResponse? = null
}