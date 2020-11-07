package com.app.unsplashapp.data.network

import com.app.unsplashapp.data.network.response.ImageResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface UnsplashApi {

    @Headers("Accept-Version: v1", "Authorization: Client-ID $ACCESS_KEY")
    @GET("/photos")
    suspend fun getImages(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
    ): List<ImageResponse>

    /*@Headers("Authorization1", "Bearer $SECRET_KEY")
    @GET("/me")
    suspend fun getUserInfo(): ResponseBody*/

    companion object {
        private const val ACCESS_KEY = "DpZxww5wfLV5becJgd7vFCmEGwsI97-DhlntO_GDm7E"
    }
}