package com.app.unsplashapp.data.network.response

import com.google.gson.annotations.SerializedName

class UserResponse {
    @SerializedName("username")
    val username: String? = null
    @SerializedName("name")
    val name: String? = null
    @SerializedName("profile_image")
    val profileImage: ImageUrlResponse? = null
    @SerializedName("location")
    val location: String? = null
}