package com.lee.mymvvmsample.network

import com.lee.mymvvmsample.network.model.ImageObj
import com.skydoves.sandwich.ApiResponse
import retrofit2.Response
import retrofit2.http.*

interface NetworkService {

    // 이미지 검색
//    @GET("api/")
//    suspend fun searchImage(
//        @Query("key") key: String,
//        @Query("q") q: String,
//        @Query("image_type") image_type: String,
//        @Query("page") page: Int,
//        @Query("per_page") per_page: Int
//    ): Response<ImageObj>

    // 이미지 검색
    @GET("api/")
    suspend fun searchImage(
        @Query("key") key: String,
        @Query("q") q: String,
        @Query("image_type") image_type: String,
        @Query("page") page: Int,
        @Query("per_page") per_page: Int
    ): ApiResponse<ImageObj>
}