package com.lee.mymvvmsample.domain

import com.lee.mymvvmsample.data.model.ImageObj
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.*

interface NetworkRepository {

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