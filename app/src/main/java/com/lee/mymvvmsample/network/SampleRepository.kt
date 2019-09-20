package com.lee.mymvvmsample.network

import com.google.gson.JsonParser
import com.lee.mymvvmsample.network.model.ListData
import com.lee.mymvvmsample.network.model.VersionResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.Response

class SampleRepository(private val service: SampleService) {

    // Version Check | service/version
    suspend fun serviceVersion(): SampleResult<VersionResponse> = withContext(Dispatchers.IO) {
        callResponse { service.serviceVersion(hashMapOf("platform" to "android")) }
    }


    private fun parseErrorResult(body: ResponseBody): SampleResult.Error {
        try {
            val element = JsonParser().parse(body.string()).asJsonObject
            val code = if (element.has("code")) element.get("code").asString else null
            val message = if (element.has("message")) element.get("message").asString else null
            return SampleResult.Error(code, message)
        } catch (e: Exception) {
        }
        return SampleResult.Error(null, null)
    }

    private suspend fun <T : Any> callResponse(call: suspend () -> Response<T>): SampleResult<T> {
        val response = call.invoke()
        if (response.isSuccessful) {
            return SampleResult.Success(response.body())
        } else {
            response.errorBody()?.let { error ->
                return parseErrorResult(error)
            } ?: run {
                return SampleResult.Error(null, null)
            }
        }
    }

    private suspend fun <T : Any> callArray(call: suspend () -> Response<List<T>>): List<T> {
        val response = call.invoke()
        return response.body() ?: emptyList()
    }

    private suspend fun <T : Any> callList(call: suspend () -> Response<ListData<T>>): ListData<T> {
        val response = call.invoke()
        return response.body() ?: ListData()
    }

}