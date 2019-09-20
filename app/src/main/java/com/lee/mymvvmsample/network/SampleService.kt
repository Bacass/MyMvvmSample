package com.lee.mymvvmsample.network

import com.lee.mymvvmsample.network.model.VersionResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface SampleService {
    // 앱 세션
    @Headers("Content-Type: application/json")
    @POST("api/service/version")
    suspend fun serviceVersion(@Body params: HashMap<String, Any>): Response<VersionResponse>
}