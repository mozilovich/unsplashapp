package com.app.unsplashapp.data.network.response

import com.google.gson.annotations.SerializedName

class ImageUrlResponse {
    @SerializedName("raw")
    val raw: String? = null
    @SerializedName("full")
    val full: String? = null
    @SerializedName("regular")
    val regular: String? = null
    @SerializedName("small")
    val small: String? = null
    @SerializedName("thumb")
    val thumb: String? = null
}