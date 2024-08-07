package com.lee.mymvvmsample.data.network

import com.google.gson.JsonParser
import com.lee.mymvvmsample.data.model.ImageObj
import com.lee.mymvvmsample.data.model.ListData
import com.lee.mymvvmsample.data.model.RequestImageParam
import com.lee.mymvvmsample.domain.NetworkRepository
import com.skydoves.sandwich.ApiResponse
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkRepositoryImpl @Inject constructor(private val repository: NetworkRepository) {

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




//    // Search Image
//    suspend fun searchImage(params: RequestImageParam): NetworkResult<ImageObj> = withContext(Dispatchers.IO) {
//        callResponse { service.searchImage(params.key!!, params.q!!, params.image_type!!, params.page!!, params.per_page!!) }
//    }

    suspend fun searchImage(params: RequestImageParam): ApiResponse<ImageObj> {
        return repository.searchImage(params.key!!, params.q!!, params.image_type!!, params.page!!, params.per_page!!)
    }
}