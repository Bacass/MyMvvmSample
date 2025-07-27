package com.lee.mymvvmsample.data.network

sealed class NetworkResult<out T : Any> {
    class Success<out T : Any>(val response: T?) : NetworkResult<T>()

    class Error(val errorCode: String?, val errorMsg: String?) : NetworkResult<Nothing>()
}
