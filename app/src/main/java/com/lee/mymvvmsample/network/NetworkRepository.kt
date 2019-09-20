package com.lee.mymvvmsample.network

import com.google.gson.JsonParser
import com.lee.mymvvmsample.network.model.ListData
import com.lee.mymvvmsample.network.model.VersionResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.Response

class NetworkRepository(private val service: NetworkService) {

    // Version Check | service/version
    suspend fun serviceVersion(): NetworkResult<VersionResponse> = withContext(Dispatchers.IO) {
        callResponse { service.serviceVersion(hashMapOf("platform" to "android")) }
    }


    private fun parseErrorResult(body: ResponseBody): NetworkResult.Error {
        try {
            val element = JsonParser().parse(body.string()).asJsonObject
            val code = if (element.has("code")) element.get("code").asString else null
            val message = if (element.has("message")) element.get("message").asString else null
            return NetworkResult.Error(code, message)
        } catch (e: Exception) {
        }
        return NetworkResult.Error(null, null)
    }

    private suspend fun <T : Any> callResponse(call: suspend () -> Response<T>): NetworkResult<T> {
        val response = call.invoke()
        if (response.isSuccessful) {
            return NetworkResult.Success(response.body())
        } else {
            response.errorBody()?.let { error ->
                return parseErrorResult(error)
            } ?: run {
                return NetworkResult.Error(null, null)
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