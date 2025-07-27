package com.lee.mymvvmsample.data.network

import com.lee.mymvvmsample.data.model.ImageObj
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageApiService {
    @GET("api/")
    suspend fun searchImage(
        @Query("key") key: String,
        @Query("q") q: String,
        @Query("image_type") imageType: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
    ): Response<ImageObj>
}
