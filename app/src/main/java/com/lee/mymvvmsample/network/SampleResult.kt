package com.lee.mymvvmsample.network

sealed class SampleResult<out T : Any> {
    class Success<out T : Any>(val response: T?) : SampleResult<T>()
    class Error(val errorCode: String?, val errorMsg: String?) : SampleResult<Nothing>()
}